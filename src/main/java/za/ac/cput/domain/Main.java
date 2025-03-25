package za.ac.cput.domain;

import za.ac.cput.domain.factory.ResidenceFactory;
import za.ac.cput.domain.factory.StaffFactory;
import za.ac.cput.domain.factory.PaymentFactory;
import za.ac.cput.domain.factory.MaintenanceRequestFactory;
import za.ac.cput.domain.factory.StudentFactory;
import za.ac.cput.domain.factory.RoomFactory;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        Residence leader = ResidenceFactory.createResidence("123456789", "Thapelo", "thape@gmail.com", "Block D");

        if (leader != null) {
            System.out.println("Leader created: " + leader);
        } else {
            System.out.println("Leader is null");
        }

        // Create student before using it in Payment
        Student student = StudentFactory.createStudent(
                "STU230640", "Lisa", "Ngozi", "lisa.ngozi@gmail.com",
                "0812345678", true, "R002",leader.getLeaderID() );

        if (student != null) {
            System.out.println("Student created: " + student);
        } else {
            System.out.println("Student is null");
        }

        // Create staff
        Staff staff = null;
        if (leader != null) {
            staff = StaffFactory.createStaff("STF123456", "Tsireledzo", "Mbedzi", "tsireledzombedzi@gmail.com", "worker", leader.getLeaderID());
        }

        if (staff != null) {
            System.out.println("Staff created: " + staff);
        } else {
            System.out.println("Staff is null");
        }

        // Create payment
        Payment payment = PaymentFactory.createPayment(
                "PAY123456", "2500", false, "2005-03-04", student.getStudentId());

        if (payment != null) {
            System.out.println("Payment successfully created: " + payment);
        } else {
            System.out.println("Failed to create Payment");
        }

        // Create maintenance request
        LocalDateTime requestDate = LocalDateTime.now();
        LocalDateTime completionDate = requestDate.plusDays(2);

        MaintenanceRequest request = new MaintenanceRequest.Builder("MR101", student.getStudentId(), "R001",
                "Broken door", requestDate)
                .status("In Progress")
                .staffId("213213")
                .resolutionNotes("Resolved by replacing the door handle")
                .completionDate(completionDate)
                .build();

        if (request != null) {
            System.out.println("Maintenance Request Created Successfully: " + request);
        } else {
            System.out.println("Failed to create Maintenance Request. Please check input values.");
        }

        // Create room (Fixed incorrect room number formatting)
        Room room = RoomFactory.createRoom("R002", 2, "Single", "Occupied", 2, "Eldorado");

        if (room != null) {
            System.out.println("Room successfully created: " + room);
        } else {
            System.out.println("Failed to create Room.");
        }
    }
}
