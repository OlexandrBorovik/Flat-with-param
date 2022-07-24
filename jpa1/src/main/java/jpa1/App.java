package jpa1;

import javax.persistence.*;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static javafx.scene.text.FontPosture.findByName;

public class App {
    static EntityManagerFactory emf;
    static EntityManager em;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            // create connection
            emf = Persistence.createEntityManagerFactory("JPATest");
            em = emf.createEntityManager();
            try {
                while (true) {
                    System.out.println("1: add flat");
                    System.out.println("2: delete flat");
                    System.out.println("3: change flat");
                    System.out.println("4: view flats");
                    System.out.println("5: view  param");
                    System.out.print("-> ");

                    String s = sc.nextLine();
                    switch (s) {
                        case "1":
                            addFlat(sc);
                            break;
                        case "2":
                            deleteFlat(sc);
                            break;
                        case "3":
                            changeFlat(sc);
                            break;
                        case "4":
                            viewFlats();
                            break;
                        case "5":
                            viewFlatsParam(sc);
                            break;
                        default:
                            return;
                    }
                }
            } finally {
                sc.close();
                em.close();
                emf.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }
    }

    private static void addFlat(Scanner sc) {
        System.out.print("Enter city: ");
        String city = sc.nextLine();
        System.out.print("Enter street: ");
        String street = sc.nextLine();
        System.out.print("Enter number of house: ");
        String num = sc.nextLine();
        int number = Integer.parseInt(num);
        System.out.print("Enter rooms: ");
        String r = sc.nextLine();
        int rooms = Integer.parseInt(r);
        System.out.print("Enter square: ");
        String sq = sc.nextLine();
        int square = Integer.parseInt(sq);
        System.out.print("Enter cost: ");
        String c = sc.nextLine();
        int cost = Integer.parseInt(c);


        em.getTransaction().begin();
        try {
            Flat flat = new Flat(city, street,number,rooms,square,cost);
            em.persist(flat);
            em.getTransaction().commit();

            System.out.println(flat.getId());
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    private static void deleteFlat(Scanner sc) {
        System.out.print("Enter flat id: ");
        String sId = sc.nextLine();
        long id = Long.parseLong(sId);

        Flat flat = em.getReference(Flat.class, id);
        if (flat == null) {
            System.out.println("Client not found!");
            return;
        }

        em.getTransaction().begin();
        try {
            em.remove(flat);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    private static void changeFlat(Scanner sc) {
        System.out.print("Enter flat id: ");
        String sId = sc.nextLine();
        long id = Long.parseLong(sId);

        Flat flat = em.getReference(Flat.class, id);
        if (flat == null) {
            System.out.println("Client not found!");
            return;
        } else {
            System.out.print("Enter city: ");
            String city = sc.nextLine();
            System.out.print("Enter street: ");
            String street = sc.nextLine();
            System.out.print("Enter number of house: ");
            String num = sc.nextLine();
            int number = Integer.parseInt(num);
            System.out.print("Enter rooms: ");
            String r = sc.nextLine();
            int rooms = Integer.parseInt(r);
            System.out.print("Enter square: ");
            String sq = sc.nextLine();
            int square = Integer.parseInt(sq);
            System.out.print("Enter cost: ");
            String c = sc.nextLine();
            int cost = Integer.parseInt(c);


            em.getTransaction().begin();

                try {
                    flat.setCity(city);
                    flat.setStreet(street);
                    flat.setNumber(number);
                    flat.setRoom(rooms);
                    flat.setSquare(square);
                    flat.setCost(cost);
                    em.getTransaction().commit();
                } catch (Exception ex) {
                    em.getTransaction().rollback();
                }
            }
        }

    private static void viewFlats() {
        Query query = em.createQuery(
                "SELECT flat FROM Flat flat", Flat.class);
        List<Flat> list = (List<Flat>) query.getResultList();

        for (Flat c : list)
            System.out.println(c);
    }

        private static void viewFlatsParam(Scanner sc) {
            System.out.print("Enter city: ");
            String citys = sc.nextLine();
            System.out.print("Enter rooms: ");
            String r = sc.nextLine();
            int room = Integer.parseInt(r);
            System.out.print("Enter square min: ");
            String sq = sc.nextLine();
            int squareMin = Integer.parseInt(sq);
            System.out.print("Enter square max: ");
            String sqm = sc.nextLine();
            Integer squareMax = Integer.parseInt(sqm);
            System.out.print("Enter minCost: ");
            String c = sc.nextLine();
            int minCost = Integer.parseInt(c);
            System.out.print("Enter maxCost: ");
            String v = sc.nextLine();
            int maxCost = Integer.parseInt(c);
            Query query = em.createQuery(" FROM Flat flat WHERE flat.city=:c AND flat.room=:r AND flat.square>=:sM AND flat.square<=:sMx AND flat.cost>=:cM AND flat.cost<=:cMx");
            query.setParameter("c",citys);
            query.setParameter("r",room);
            query.setParameter("sM",squareMin);
            query.setParameter("sMx",squareMax);
            query.setParameter("cM",minCost);
            query.setParameter("cMx",maxCost);
            List<Flat> list = (List<Flat>) query.getResultList();
            if (list.size() == 0) {
                System.out.println("flat not found.");
            } else {
                for (Flat e : list)
                    System.out.println(e);
            }
        }

}


