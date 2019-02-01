//import javax.swing.text.View;
//import sun.awt.image.ImageWatched;

import com.sun.deploy.util.SyncAccess;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class LogAnalyzer
{
      //constants to be used when pulling data out of input
      //leave these here and refer to them to pull out values
    private static final String START_TAG = "START";
    private static final int START_NUM_FIELDS = 3;
    private static final int START_SESSION_ID = 1;
    private static final int START_CUSTOMER_ID = 2;
    private static final String BUY_TAG = "BUY";
    private static final int BUY_NUM_FIELDS = 5;
    private static final int BUY_SESSION_ID = 1;
    private static final int BUY_PRODUCT_ID = 2;
    private static final int BUY_PRICE = 3;
    private static final int BUY_QUANTITY = 4;
    private static final String VIEW_TAG = "VIEW";
    private static final int VIEW_NUM_FIELDS = 4;
    private static final int VIEW_SESSION_ID = 1;
    private static final int VIEW_PRODUCT_ID = 2;
    private static final int VIEW_PRICE = 3;
    private static final String END_TAG = "END";
    private static final int END_NUM_FIELDS = 2;
    private static final int END_SESSION_ID = 1;

      //a good example of what you will need to do next
      //creates a map of sessions to customer ids
    private static void processStartEntry(
        final String[] words,
        final Map<String, List<String>> sessionsFromCustomer)
    {
        if (words.length != START_NUM_FIELDS)
        {
            return;
        }

         //check if there already is a list entry in the map
         //for this customer, if not create one
        List<String> sessions = sessionsFromCustomer
         .get(words[START_CUSTOMER_ID]);
        if (sessions == null)
        {
            sessions = new LinkedList<>();
            sessionsFromCustomer.put(words[START_CUSTOMER_ID], sessions);
        }

         //now that we know there is a list, add the current session
        sessions.add(words[START_SESSION_ID]);
    }

      //similar to processStartEntry, should store relevant view
      //data in a map - model on processStartEntry, but store
      //your data to represent a view in the map (not a list of strings)
    private static void processViewEntry(final String[] words, final Map<String, List<View>> sessionView
      /* add parameters as needed */
        )
    {
        if (words.length != VIEW_NUM_FIELDS){
            return;
        }

        List<View> viewings = sessionView.get(words[VIEW_SESSION_ID]);
        if (viewings==null){
            viewings = new LinkedList<>();
            sessionView.put(words[VIEW_SESSION_ID],viewings);
        }

        viewings.add(new View(words[VIEW_SESSION_ID],words[VIEW_PRODUCT_ID],Integer.parseInt(words[VIEW_PRICE])));

    }

      //similar to processStartEntry, should store relevant purchases
      //data in a map - model on processStartEntry, but store
      //your data to represent a purchase in the map (not a list of strings)
    private static void processBuyEntry(final String[] words, final Map<String, List<Buy>> buyFromSession
      /* add parameters as needed */
      )
    {
        if (words.length != BUY_NUM_FIELDS){
            return;
        }

        List<Buy> buys = buyFromSession.get(words[BUY_SESSION_ID]);
        if (buys==null){
            buys= new LinkedList<>();
            buyFromSession.put(words[BUY_SESSION_ID],buys);
        }

        buys.add(new Buy(words[BUY_SESSION_ID],words[BUY_PRODUCT_ID],Integer.parseInt(words[BUY_PRICE]),Integer.parseInt(words[BUY_QUANTITY])));
    }

    private static void processEndEntry(final String[] words)
    {
        if (words.length != END_NUM_FIELDS)
        {
            return;
        }
    }

      //this is called by processFile below - its main purpose is
      //to process the data using the methods you write above
    private static void processLine(final String line,
    final Map<String, List<String>> sessionsFromCustomer,
    final Map<String, List<View>> viewSession,
    final Map<String, List<Buy>> buyFromSession)
    {
        final String[] words = line.split("\\h");

        if (words.length == 0)
        {
            return;
        }

        switch (words[0])
        {
            case START_TAG:
                processStartEntry(words, sessionsFromCustomer);
                break;
            case VIEW_TAG:
                processViewEntry(words, viewSession );
                break;
            case BUY_TAG:
                processBuyEntry(words, buyFromSession);
                break;
            case END_TAG:
                processEndEntry(words);
                break;
        }
    }

      //write this after you have figured out how to store your data
      //make sure that you understand the problem
   private static void printSessionPriceDifference(final Map<String,List<String>> sessionCustomer,
        final Map<String, List<View>> viewSessions,
        final Map<String, List<Buy>> buyFromSession
        /* add parameters as needed */
        )
   {
        System.out.println("Price Difference for Purchased Product by Session");


        for (List<String> session: sessionCustomer.values()){

            //For 1 session
            for (String sessionNumber : session){
                if (buyFromSession.containsKey(sessionNumber)){
                    System.out.println(sessionNumber);
                    for (Buy buyObject: buyFromSession.get(sessionNumber)){
                        int viewTotal=0;
                        int howManyViews=0;
                        int buyPrice = buyObject.getPrice();
                        if (viewSessions.containsKey(sessionNumber)){
                            for (View viewObject : viewSessions.get(sessionNumber)){
                                viewTotal+=viewObject.getPrice();
                                howManyViews++;
                            }
                        }
                        double average = buyPrice - (double)viewTotal/howManyViews ;
                        System.out.println("\t"+ buyObject.getProductNumber() + " " + average);
                    }
                }
            }
        }
      /* add printing */
   }

      //write this after you have figured out how to store your data
      //make sure that you understand the problem
   private static void printCustomerItemViewsForPurchase(final Map<String, List<String>> sessionsFromCustomer,
                                                         final Map<String, List<View>> viewSession,
                                                         final Map<String, List<Buy>> buyFromSession
      /* add parameters as needed */
      ) {
       System.out.println();
       System.out.println("Number of Views for Purchased Product by Customer");
//       for (View item:viewSession.get("session2")){
//           System.out.println(item.getSessionNumber());
//       }
       for (String customer : sessionsFromCustomer.keySet()) {
           List<String> sessions = sessionsFromCustomer.get(customer);
           Map<String, Integer> countMap = new HashMap<>();
           for (String customerSession : sessions) {
               if (buyFromSession.containsKey(customerSession)) {
                   System.out.println(customer);
                   for (Buy boughtProduct : buyFromSession.get(customerSession)) {
                       //List<View> viewedBoughtProductList = viewSession.get(customerView);
                       //if (viewSession.containsKey(customerView)) {
                       //Looping twice b/c customer1 bought 2 items
                       //System.out.println("BoughtProduct: " + boughtProduct.getProductNumber());
                       List<String> sessionsAlreadyViewed = new LinkedList<String>();
                       for (String customerSessionBought : sessions){
                           List<View> viewedSessionList=viewSession.get(customerSessionBought);
                           //System.out.println("here");
                           if (viewedSessionList!=null) {
                               for (View viewedSession : viewedSessionList) {
                                   //System.out.println("yeet");
                                   //System.out.println(viewedSession.getProductNumber());
                                   if (viewedSession.getProductNumber().equals(boughtProduct.getProductNumber())) {
                                       //System.out.println("Here");
                                       if (!(countMap.containsKey(viewedSession.getProductNumber()))) {
                                           countMap.put(viewedSession.getProductNumber(), 1);
                                           sessionsAlreadyViewed.add(viewedSession.getSessionNumber());
                                           //                                       System.out.println(sessionsAlreadyViewed);
                                       }
                                       if (countMap.containsKey(viewedSession.getProductNumber())) {
                                           //if (!(viewedSession.getSessionNumber().equals(boughtProduct.getSessionNumber()))) {
                                           //                                       for (String sessiontemp: sessionsAlreadyViewed) {
                                           //System.out.println(sessiontemp);

                                           //if (!(viewedSession.getSessionNumber().equals(sessiontemp))) {
                                           if (!(sessionsAlreadyViewed.contains(viewedSession.getSessionNumber()))) {
                                               //                                           System.out.println(viewedSession.getSessionNumber() + " vs " + viewedSessionList);
                                               countMap.put(viewedSession.getProductNumber(), countMap.get(viewedSession.getProductNumber()) + 1);
                                               sessionsAlreadyViewed.add(viewedSession.getSessionNumber());
                                               //System.out.println("yeet");
                                           }
                                           //}
                                       }
                                   }
                               }
                           }
                       }
                   }
               }
//               System.out.println("Values: " + countMap.values() );
//               System.out.println("Keys: " + countMap.keySet() );
//               for (String productID : countMap.keySet()) {
//                   System.out.println("\t" + productID + " " + countMap.get(productID));
//               }


           }
//       System.out.println("Values: " + countMap.values() );
//       System.out.println("Keys: " + countMap.keySet() );
       for (String productID : countMap.keySet()) {
           System.out.println("\t" + productID + " " + countMap.get(productID));
       }
       }
   }
//       for (String i: sessionsFromCustomer.keySet()) {
//           List<String> listofproducts = new LinkedList<>();
//
//           List<String> listofsessions = sessionsFromCustomer.get(i);
//
//           for (String session : listofsessions) {
//               List<Buy> listofbuys = buyFromSession.get(session);
//               if (listofbuys != null) {
//                   System.out.println(i);
//                   for (Buy product : listofbuys) {
//                       if (!listofproducts.contains(product)) {
//                           listofproducts.add(product.getProductNumber());
//                       }
//                   }
//               }
//           }
//
//
//           for (String product : listofproducts) {
//               int countviews = 0;
//               for (String session : sessionsFromCustomer.get(i)) {
//                   List<View> viewList = viewSession.get(session);
//                   if (viewList != null) {
//                       for (View view : viewSession.get(session)) {
//                           if (view.getProductNumber().equals(product)) {
//                               countviews += 1;
//                               break;
//                           }
//                       }
//                   }
//               }
//               /* add printing */
//               System.out.println("\t" + product + " " + countviews);
//           }
//       }
//       System.out.println('\n');
//       for (List<String> session: sessionsFromCustomer.values()) {
//
//           //For 1 session
//           for (String sessionNumber : session) {
//               if (buyFromSession.containsKey(sessionNumber)) {
//                   for (Buy buyObject: buyFromSession.get(sessionNumber)) {
//                       System.out.println();
//                       List<String> productsViewed = new LinkedList<>();
//                       if (viewSession.containsKey(sessionNumber)) {
//
//                           for (View viewObject : viewSession.get(sessionNumber)) {
//                               if (productsViewed.contains(viewObject.getProductNumber())){
//                               }
//                               else{
//                                   productsViewed.add(viewObject.getProductNumber());
//                               }
//                           }
//                       }
//                       for (String product:productsViewed){
//                           System.out.println("\t" + product + " " + productsViewed.size());
//                       }
//                   }
//               }
//           }
//       }

//       for (String customer: sessionsFromCustomer.keySet()){
//            List<String> sessions = sessionsFromCustomer.get(customer);
//           //For 1 customer
//           System.out.println(customer);
//           for (String sessionOfCustomer : sessions){
//               if (buyFromSession.containsKey(sessionOfCustomer)) {
//                   for (Buy buyObject: buyFromSession.get(sessionOfCustomer)) {
////                       System.out.println(customer);
//                        List<String> boughtProducts = new LinkedList<>();
//                        boughtProducts.add(buyObject.getProductNumber());
//
//                       //i have product1 and product3 in boughtproducts rn
////                        for (String x:boughtProducts){
////                            System.out.println(x);
////                        }
//                        int counter=0;
//                        List<String> productsViewed = new LinkedList<>();
//                        for (View viewProduct:viewSession.get(buyObject.getSessionNumber())){
//                            if (!productsViewed.contains(viewProduct.getProductNumber())){
//                                productsViewed.add(viewProduct.getProductNumber());
//                                counter+=1;
//                            }
//                            else{
//                                continue;
//                            }
//                        }
//                        for (String product : boughtProducts){
//                           System.out.println("\t" + product + " " + counter);
//                        }
////                       List<String> productsViewed = new LinkedList<>();
////                       //System.out.println(productNumber);
////                       for (View viewedProduct : viewSession.get(buyObject.getSessionNumber())) {
////                           if (productsViewed.contains(viewedProduct.getProductNumber())&& buyObject.getProductNumber().equals(viewedProduct.getProductNumber())) {
////                           }
////                           else{
////                               productsViewed.add(viewedProduct.getProductNumber());
////                           }
////                       }
////                       for (String product : productsViewed){
////                           System.out.println("\t" + product + " " + productsViewed.size());
////                       }
//                   }
//               }
//           }
//       }
//       System.out.println();
//   }



      //write this after you have figured out how to store your data
      //make sure that you understand the problem
    private static void printStatistics(final Map<String, List<String>> sessionsFromCustomer,
                                       final Map<String, List<View>> viewSession,
                                       final Map<String, List<Buy>> buyFromSession
      /* add parameters as needed */
      )
    {
        List<String> noPurchase = new LinkedList<>();
        int viewWithoutPurchaseCount=0;

        for (List<String> listofSession : sessionsFromCustomer.values()){
            for (String session : listofSession){
                List<Buy> buyObjects = buyFromSession.get(session);
                if (buyObjects==null){
                    noPurchase.add(session);
                }
            }
        }

        for (String session : noPurchase){
            List<View> listOfViewObjects = viewSession.get(session);
            if(listOfViewObjects!=null){
                viewWithoutPurchaseCount+= listOfViewObjects.size();
            }
        }
        double average = (double)viewWithoutPurchaseCount/noPurchase.size();


        System.out.println();
        System.out.println("Average Views without Purchase:" + average);
        System.out.println();
        printSessionPriceDifference(sessionsFromCustomer,viewSession,buyFromSession /*add arguments as needed */);
        printCustomerItemViewsForPurchase(sessionsFromCustomer,viewSession,buyFromSession /*add arguments as needed */);
        System.out.println();

      /* This is commented out as it will not work until you read
         in your data to appropriate data structures, but is included
         to help guide your work - it is an example of printing the
         data once propogated 
         printOutExample(sessionsFromCustomer, viewsFromSession, buysFromSession);
      */
		
    }

   /* provided as an example of a method that might traverse your
      collections of data once they are written 
      commented out as the classes do not exist yet - write them! */

    private static void printOutExample(
        final Map<String, List<String>> sessionsFromCustomer,
        final Map<String, List<View>> viewsFromSession,
        final Map<String, List<Buy>> buysFromSession) {
        //for each customer, get their sessions
        //for each session compute views
        for (Map.Entry<String, List<String>> entry :
                sessionsFromCustomer.entrySet()) {
            System.out.println(entry.getKey());
            List<String> sessions = entry.getValue();
            for (String sessionID : sessions) {
                System.out.println("\tin " + sessionID);
                List<View> theViews = viewsFromSession.get(sessionID);
                if (theViews != null) {
                    for (View thisView : theViews) {
                        System.out.println("\t\tviewed " + thisView.getProductNumber());
                    }
                }
            }
        }
    }

      //called in populateDataStructures
    private static void processFile(
        final Scanner input,
        final Map<String, List<String>> sessionsFromCustomer,
        final Map<String, List<View>> viewSession,
        final Map<String, List<Buy>> buyFromSession)
    {
        while (input.hasNextLine())
        {
            processLine(input.nextLine(), sessionsFromCustomer, viewSession,buyFromSession);
        }
    }

      //called from main - mostly just pass through important data structures	
    private static void populateDataStructures(
        final String filename,
        final Map<String, List<String>> sessionsFromCustomer,
        final Map<String, List<View>> viewSession,
        final Map<String, List<Buy>> buyFromSession
        /* add parameters as needed */
        )
        throws FileNotFoundException
    {
        try (Scanner input = new Scanner(new File(filename)))
        {
            processFile(input, sessionsFromCustomer, viewSession, buyFromSession
            /* add arguments as needed */ );
        }
    }

    private static String getFilename(String[] args)
    {
        if (args.length < 1)
        {
            System.err.println("Log file not specified.");
            System.exit(1);
        }

        return args[0];
    }

    public static void main(String[] args)
    {
      /* Map from a customer id to a list of session ids associated with
       * that customer.
       */
        final Map<String, List<String>> sessionsFromCustomer = new HashMap<>();
        final Map<String, List<View>> viewSession= new HashMap<>();
        final Map<String, List<Buy>> buyFromSession= new HashMap<>();

      /* create additional data structures to hold relevant information */
      /* they will most likely be maps to important data in the logs */

        final String filename = getFilename(args);

        try
        {
            populateDataStructures(filename, sessionsFromCustomer, viewSession,buyFromSession
            /* add parameters as needed */
                );
            printStatistics( sessionsFromCustomer,viewSession,buyFromSession
            /* add parameters as needed */
                );
        }
        catch (FileNotFoundException e)
        {
            System.err.println(e.getMessage());
        }
        printOutExample(sessionsFromCustomer,viewSession,buyFromSession);
    }
}
