package ar.com.javacuriosities.concurrency.fork_join_pool;

import java.util.concurrent.RecursiveAction;

public class NewUserTask extends RecursiveAction {


    @Override
    protected void compute() {
        System.out.println("New User Task - " + Thread.currentThread().getName());
        invokeAll(new CreateUserCredentialsTask(), new CreateUserEmailTask(), new UploadUserImageTask());
    }

    private static final class CreateUserCredentialsTask extends RecursiveAction {

        @Override
        protected void compute() {
            try {
                Thread.sleep(1000);
                System.out.println("Create User Credentials Task - " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                // Log and Handle exception
                e.printStackTrace();
            }
        }
    }

    private static final class CreateUserEmailTask extends RecursiveAction {

        @Override
        protected void compute() {
            try {
                Thread.sleep(1000);
                System.out.println("Create User Email Task - " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                // Log and Handle exception
                e.printStackTrace();
            }
        }
    }

    private static final class UploadUserImageTask extends RecursiveAction {

        @Override
        protected void compute() {
            try {
                Thread.sleep(1000);
                System.out.println("Upload User Image Task - " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                // Log and Handle exception
                e.printStackTrace();
            }
        }
    }
}
