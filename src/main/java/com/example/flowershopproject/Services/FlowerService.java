package com.example.flowershopproject.Services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.apache.commons.io.FileUtils;
import com.example.flowershopproject.Controller.FloristmainController;
import com.example.flowershopproject.Exceptions.*;
import com.example.flowershopproject.Actions.Flower;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class FlowerService {
    private static List<Flower> flowers;
    private static final Path FLOWERS_PATH = FileSystemService.getPathToFile(new String[]{"config", "flower.json"});

    public FlowerService() {
    }

    public static void loadFlowerFromFile() throws IOException {
        if (!Files.exists(FLOWERS_PATH, new LinkOption[0])) {
         //   FileUtils.copyURLToFile(FlowerService.class.getClassLoader().getResource("flowers.json"), FLOWERS_PATH.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        flowers = (List)objectMapper.readValue(FLOWERS_PATH.toFile(), new TypeReference<List<Flower>>() {
        });
    }
    public static void addFlower(String flowername, String imgurl, int quantity, double price, int numItems) throws FlowerAlreadyExists {
        String floristuser= FloristmainController.getFlorist().getUsername();
        String floristname = FloristmainController.getFlorist().getName();
        checkFlowerDoesNotAlreadyExist(flowername,floristuser);
        flowers.add(new Flower(floristname, flowername, floristuser, imgurl, quantity, price, numItems));
        persistFlowers();
    }


    private static void checkFlowerDoesNotAlreadyExist(String flowername, String floristuser) throws FlowerAlreadyExists {

        Iterator var1 = flowers.iterator();

        Flower flower;
        do {
            if (!var1.hasNext()) {
                return;
            }

            flower = (Flower) var1.next();
        } while (!Objects.equals(flowername, flower.getFlowerName()));
        if (Objects.equals(floristuser, flower.getFloristUsername()))
            throw new FlowerAlreadyExists(flowername);
    }
    private static void persistFlowers() {
     //   try {
            ObjectMapper objectMapper = new ObjectMapper();
         //   objectMapper.floristWithDefaultPrettyPrinter().writeValue(FLOWERS_PATH.toFile(), flowers);
     //   } catch (IOException var1) {
       //     throw new CouldNotWriteFlowersException();
        }
   // }
    public static List<Flower> getFlowers(String floristuser)throws Exception {
        FlowerService.loadFlowerFromFile();
        List<Flower> flower = new ArrayList<>();
        for (Flower pr : flowers){
            if (Objects.equals(floristuser, pr.getFloristUsername())){
                flower.add(pr);
            }
        }
        return flower;

    }
    public static void updateNumberOfItems (Flower bk) throws FlowerNotInStock{

        for (Flower p: flowers){
            if(p.equals(bk)){
                if (p.getNoOfItems()!=0){
                    p.setNoOfItems(p.getNoOfItems()-1);
                    FlowerService.persistFlowers();
                }
                else throw new FlowerNotInStock();
            }
        }

    }
    public static void editFlower (Flower bk, String name, String ImgURL, int quant, int nr, double price) {
        for (Flower p: flowers) {
            if (p.equals(bk))
            {
                p.setFlowerName(name);
                p.setImageUrl(ImgURL);
                p.setQuantity(quant);
                p.setNoOfItems(nr);
                p.setPrice(price);

                FlowerService.persistFlowers();
            }
        }
    }
    public static void deleteFlower (Flower p)
    {

        Iterator<Flower> iter = flowers.iterator();

        while (iter.hasNext()) {
            Flower bk = iter.next();

            if (bk.equals(p)) {
                iter.remove();
                FlowerService.persistFlowers();
            }

        }


    }
}
