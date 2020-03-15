package resourceManager;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ComplexResourceManager implements ResourceManager {

    /**
     * The resource manged by this resource manager.
     */
    private Resource resource;

    /**
     * The maximum priority with which the resource can be requested. Valid priorities are in the interval [0,MAX_PRIORITY]
     */
    private static final int MAX_PRIORITY = 10;
    /**
     * The number of priority levels.
     */
    public static final int NO_OF_PRIORITIES = MAX_PRIORITY+1;
    /**
     * The number of users waiting at each resource level.
     */
    private int numberWaiting[] = new int[NO_OF_PRIORITIES];

    /**
     * This value should be returned by the releaseResource() method if no waiting resource user can be found.
     */
    public static final int NONE_WAITING = -1;

    /**
     * Generates random priority levels.
     */
    private Random random = new Random(System.currentTimeMillis());

    /**
     * The number of users using this resource.
     * This should never be more than one.
     */
    private int numberOfUsers;

    /**
     * The number of times that the resource can still be used.  The resource becomes
     * unavailable when this reaches zero.
     */
    private int usesLeft;

    private int count;

    private final Lock lock = new ReentrantLock();

    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();
    /**
     * Set the resource and initialise the numbers of waiting processes, and the number of users, to zero.
     * @param resource the resource managed by this manager
     * @param maxUses the maximum number of uses permitted for this manager's resource.
     * The actual number of uses permitted for the resource is set to a random value in the range (0,maxUses].
     */
    ;
    public ComplexResourceManager(Resource resource,int maxUses) {
        this.resource = resource;
        for (int priority = 0; priority < NO_OF_PRIORITIES; priority++) {
            numberWaiting[priority] = 0;
        }
        numberOfUsers = 0;
        usesLeft = random.nextInt(maxUses)+1;
    }

    /**
     * Get the name of the resource managed by this resource manager.
     * @return the name of the resource managed by this resource manager.
     */
    public String getResourceName() {
        return resource.toString();
    }

    /**
     * Note an increase, by one, in the number of processes waiting with a given priority.
     * @param priority the priority for which the increase should be noted.
     * @return the next number of users of the given priority noted as waiting
     */
    public int increaseNumberWaiting(int priority) {
        numberWaiting[priority]++;
        return numberWaiting[priority];
    }

    /**
     * Note a decrease, by one, in the number of processes waiting with a given priority.
     * @param priority the priority for which the decrease should be noted.
     * @return the new number of users of the given priority noted as waiting
     */
    public int decreaseNumberWaiting(int priority) {
        numberWaiting[priority]--;
        return numberWaiting[priority];
    }

    /**
     * Get the number of users of a given priority noted as waiting.
     * @param priority the priority of which the number of users noted as waiting is required.
     * @return the number of users of a given priority noted as waiting.
     */
    public int getNumberWaiting(int priority) {
        return numberWaiting[priority];
    }

    /**
     * Generate a random priority in the permitted range.
     * @return a random priority from the interval [0,MAX_PRIORITY].
     */
    public int getRandomPriority() {
        return random.nextInt(MAX_PRIORITY+1);
    }

    /**
     * Check whether the resource is exhausted.
     * @return true iff the resource is exhausted.
     */
    public boolean resourceIsExhausted() {
        return usesLeft <= 0;
    }

    @Override
    public void requestResource(int priority) throws ResourceError {
        lock.lock();
          try {
              while (count == numberWaiting.length) notFull.await();
              numberWaiting[numberOfUsers] = priority;
              numberOfUsers = (numberOfUsers + 1) % numberWaiting.length;
              count++;
              notEmpty.signal();
          } catch (InterruptedException e) {
              e.printStackTrace();
          } finally {
              lock.unlock();
          }
    }

    /**
     * Allow the resource to be used for a specified length of time.
     * Calls of this method <i>must</i> be protected by (properly implemented) calls of
     * requestResource() and releaseResource().
     * @param timeRequired the time, in milliseconds, for which the requesting user requires use of the resource.
     * @throws ResourceError if more than one user is using the resource, or if the resource is exhausted.
     */
    public void useResource(int timeRequired) throws ResourceError {
        numberOfUsers++;
        if (numberOfUsers > 1) {
            throw new ResourceError(((ResourceUser) Thread.currentThread()) + " cannot use " + resource + " because it is already in use by another user");
        }
        if (resourceIsExhausted()) {
            System.out.println(((ResourceUser) Thread.currentThread()) + " cannot use " + resource + " as the resource is exhausted");
        } else {
            resource.use(timeRequired);
            usesLeft--;
        }
        System.out.println(resource + " has " + usesLeft + " uses left");
        numberOfUsers--;
    }

    @Override
    public int releaseResource() throws ResourceError {
     lock.lock();
        try{
          while(count == 0) notEmpty.await();
            int priority = numberWaiting[usesLeft];
           usesLeft = (usesLeft+1)%numberWaiting.length;
           count--;
           notFull.signal();
            return priority;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return 0;
    }
}
