package az.code;







import az.code.service.MarketService;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        MarketService marketService=new MarketService();
        marketService.menu();
    }

}
