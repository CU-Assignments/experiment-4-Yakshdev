import java.util.*;

class TicketBookingSystem {
    private final int totalSeats = 10;
    private final boolean[] seats = new boolean[totalSeats];

    public synchronized boolean bookSeat(int seatNumber) {
        if (seatNumber < 0 || seatNumber >= totalSeats) {
            System.out.println(Thread.currentThread().getName() + ": Invalid Seat Number");
            return false;
        }
        if (seats[seatNumber]) {
            System.out.println(Thread.currentThread().getName() + ": Seat " + (seatNumber + 1) + " Already Booked!");
            return false;
        }
        seats[seatNumber] = true;
        System.out.println(Thread.currentThread().getName() + ": Successfully Booked Seat " + (seatNumber + 1));
        return true;
    }
}

class TicketBookingThread extends Thread {
    private final TicketBookingSystem system;
    private final int seatNumber;

    TicketBookingThread(TicketBookingSystem system, int seatNumber, String name, int priority) {
        super(name);
        this.system = system;
        this.seatNumber = seatNumber;
        setPriority(priority);
    }

    public void run() {
        system.bookSeat(seatNumber);
    }
}

public class TicketBookingApp {
    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem();
        List<TicketBookingThread> customers = new ArrayList<>();

        customers.add(new TicketBookingThread(system, 2, "VIP-Customer-1", Thread.MAX_PRIORITY));
        customers.add(new TicketBookingThread(system, 5, "Regular-Customer-1", Thread.NORM_PRIORITY));
        customers.add(new TicketBookingThread(system, 2, "Regular-Customer-2", Thread.NORM_PRIORITY));
        customers.add(new TicketBookingThread(system, 7, "VIP-Customer-2", Thread.MAX_PRIORITY));
        customers.add(new TicketBookingThread(system, 5, "Regular-Customer-3", Thread.NORM_PRIORITY));

        for (TicketBookingThread customer : customers) {
            customer.start();
        }

        for (TicketBookingThread customer : customers) {
            try {
                customer.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Booking Process Completed.");
    }
}
