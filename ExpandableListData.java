package com.myproject.myhub.giveawayhub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListData
{
  public static HashMap<String, List<String>> getData()
  {
    HashMap localHashMap = new HashMap();
    ArrayList localArrayList1 = new ArrayList();
    localArrayList1.add("Vacuum Cleaners");
    localArrayList1.add("Oven");
    localArrayList1.add("Fridge");
    localArrayList1.add("AC");
    localArrayList1.add("Fans");
    ArrayList localArrayList2 = new ArrayList();
    localArrayList2.add("Tables");
    localArrayList2.add("Chairs");
    localArrayList2.add("Cots");
    localArrayList2.add("Beds");
    localArrayList2.add("Coffee Maker");
    localArrayList2.add("Toaster");
    localArrayList2.add("Sofa");
    localArrayList2.add("Pillows");
    localArrayList2.add("Blankets");
    localArrayList2.add("Curtains");
    ArrayList localArrayList3 = new ArrayList();
    localArrayList3.add("Cooking Utensils");
    localArrayList3.add("Cutlery");
    ArrayList localArrayList4 = new ArrayList();
    localArrayList4.add("Music System");
    localArrayList4.add("DVD Player");
    localArrayList4.add("TV");
    localArrayList4.add("Laptops");
    localArrayList4.add("Clothes");
    ArrayList localArrayList5 = new ArrayList();
    localArrayList5.add("Books");
    ArrayList localArrayList6 = new ArrayList();
    localArrayList6.add("Organizer");
    localArrayList6.add("Laundry Basket");
    localArrayList6.add("Wardrobe");
    localArrayList6.add("Shoe Rack");
    localArrayList6.add("Bicycles");
    localArrayList6.add("Bed Lamp");
    localArrayList6.add("Ladders");
    localArrayList6.add("Tool Kit");
    localArrayList6.add("Pet Accessories");
    localArrayList6.add("Gardening Accessories");
    localArrayList6.add("Bags");
    localArrayList6.add("Travel Bags");
    localHashMap.put("Home Appliances", localArrayList1);
    localHashMap.put("Furniture", localArrayList2);
    localHashMap.put("Kitchenware", localArrayList3);
    localHashMap.put("Electronics", localArrayList4);
    localHashMap.put("Stationary", localArrayList5);
    localHashMap.put("Utilities", localArrayList6);
    return localHashMap;
  }
}