package model;

public class Driver {
        public static void main(String[] args) {
        Customer customer = new Customer("Bindu","Thotakura","bindu@gmail.com");
        System.out.println(customer);

        String upadatedEmail = customer.setEmail("upadatedemail@gmail.com");
        System.out.println(upadatedEmail);

    /*Checking IllegalArgumentException*/
//        Customer customer1 = new Customer("Rock","Star","bindu@gmail");
//        System.out.println(customer1);
    }
}
