package Singleton;

// CPU level variables: when 2 threads are running on different cores, values are not instantly updated to CPU memory
// updated values are kept in local CPU storage
// hence if 2 threads acquire lock, 1 of them initializes instance but propagation of values has not happened from local to main memory
// so t2 mights also creates new instance, hence we need "volatile" so any change made to this variable is instantly visible to all threads

// "volatile" keyword ensures that variable is read & written to main memory bypassing thread-local caches
// useful when try to store some "state" in system
// not used much as most of services are state-less (req-res cycle), so everything starts & end in same thread, across thread there is not much interaction at service level

public class CustomSingleton {
    private static volatile CustomSingleton instance;

    private CustomSingleton(){

    }

    public static CustomSingleton getInstance(){
        // as reader threads need not acquire the lock
        if(instance != null){
            return instance;
        }

        synchronized (CustomSingleton.class){
            if(instance == null) {
                instance = new CustomSingleton();
            }
        }
        return instance;
    }
}
