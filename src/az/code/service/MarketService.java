package az.code.service;

import az.code.model.Product;
import az.code.model.ProductType;
import az.code.model.Purchase;
import az.code.model.PurchaseItem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MarketService {
    private List<Product> products;
    private List<Purchase> purchases;
    Scanner scanner = new Scanner(System.in);


    public MarketService() {
        products = new ArrayList<>();
        purchases=new ArrayList<>();
    }
public List<Purchase> getPurchases(){
        return purchases;
}
    public List<Product> getProducts() {
        return products;
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        int mainChoice = 0;

        while (mainChoice != 3) {
            System.out.println("Zəhmət olmasa bir əməliyyat seçin:");
            System.out.println("1. Məhsullar üzərində əməliyyatlar");
            System.out.println("2. Satış üzərində əməliyyatlar");
            System.out.println("3. Sistemdən çıx");

            if (scanner.hasNextInt()) {
                mainChoice = scanner.nextInt();

                switch (mainChoice) {
                    case 1:
                        productOperationsMenu(scanner);
                        break;
                    case 2:
                        salesOperationsMenu();
                        break;
                    case 3:
                        System.out.println("Sistemdən çıxılır.");
                        break;
                    default:
                        System.out.println("Yanlış bir seçim elədiniz. Zəhmət olmasa yenidən seçin.");
                        break;
                }
            } else {
                System.out.println("Geçersiz giriş. Lütfen bir sayı girin.");
                scanner.next();
            }
        }

        scanner.close();
    }

    public void productOperationsMenu(Scanner scanner) {
        int productChoice = 0;

        while (productChoice != 8) {
            System.out.println("Məhsullar üzərində əməliyyatlar üçün bir seçim edin:");
            System.out.println("1. Yeni məhsul əlavə et");
            System.out.println("2. Məhsul üzərində düzəliş et");
            System.out.println("3. Məhsulu sil");
            System.out.println("4. Bütün məhsulları göstər");
            System.out.println("5. Kateqoriyasına görə məhsulları göstər");
            System.out.println("6. Qiymət aralığına görə məhsulları göstər");
            System.out.println("7. Məhsullar arasında adə görə axtarış et");
            System.out.println("8. Geriyə qayıt");

            if (scanner.hasNextInt()) {
                productChoice = scanner.nextInt();

                switch (productChoice) {
                    case 1:
                        addProduct(scanner);
                        break;
                    case 2:
                        editProduct(scanner);
                        break;
                    case 3:

                        deleteProductByCode(scanner);
                        break;
                    case 4:

                        showAllProducts();
                        break;
                    case 5:

                        System.out.println("Kateqoriyanı seçin (FOOD, DRINK, SWEET, MEAT): ");
                        scanner.nextLine();
                        String selectedCategory = scanner.nextLine().toUpperCase();
                        ProductType category = ProductType.valueOf(selectedCategory);
                        showProductsByCategory(category);
                        break;
                    case 6:
                        System.out.println("Minimum qiyməti daxil edin: ");
                        double minPrice = scanner.nextDouble();
                        System.out.println("Maksimum qiyməti daxil edin: ");
                        double maxPrice = scanner.nextDouble();
                        showProductsByPriceRange(minPrice, maxPrice);
                        break;
                    case 7:
                        System.out.println("Axtarmaq istədiyiniz məhsulun adını daxil edin: ");
                        scanner.nextLine();
                        String searchName = scanner.nextLine();
                        searchProductByName(searchName);
                        break;
                    case 8:
                        System.out.println("Əməliyyat menyunun ana səhifəsinə geri qayıdılır.");
                        break;
                    default:
                        System.out.println("Yanlış bir seçim elediniz. Zəhmət olmasa yenidən seçin.");
                        break;
                }
            } else {
                System.out.println("Geçersiz giriş. Lütfen bir sayı girin.");
                scanner.next();
            }
        }
    }
    public void salesOperationsMenu() {
        int salesChoice = 0;

        while (salesChoice != 8) {
            System.out.println("Satışlar üzərində əməliyyatlar üçün bir seçim edin:");
            System.out.println("1. Yeni satış əlavə et");
            System.out.println("2. Satışdan məhsulun geri qaytarılması");
            System.out.println("3. Satışın silinməsi");
            System.out.println("4. Bütün satışları göstər");
            System.out.println("5. Verilmiş tarix aralığındakı satışları göstər");
            System.out.println("6. Verilmiş mebleğ aralığındakı satışları göstər");
            System.out.println("7. Verilmiş tarixdə olan satışları göstər");
            System.out.println("8. Verilmiş nömrəyə əsasən satışın məlumatlarını göstər");

            if (scanner.hasNextInt()) {
                salesChoice = scanner.nextInt();

                switch (salesChoice) {
                    case 1:
                        createNewSale(scanner);
                        break;
                    case 2:
                   returnProductFromSale(scanner);
                        break;
                    case 3:
                      deleteSaleByNumber(scanner);
                        break;
                    case 4:
                       showAllSales();
                        break;
                    case 5:
                        showSalesByDateRange(scanner);
                        break;
                    case 6:
                        System.out.println("Minimum mebleği daxil edin: ");
                        double minAmount = scanner.nextDouble();
                        System.out.println("Maksimum mebleği daxil edin: ");
                        double maxAmount = scanner.nextDouble();
                        showSalesByAmountRange(minAmount, maxAmount);
                        break;
                    case 7:
                        showSalesByDate(scanner);
                        break;
                    case 8:
                        showSaleDetailsByNumber(scanner);
                        break;
                    default:
                        System.out.println("Sehv secim etdiniz. Zəhmət olmasa yenidən seçin.");
                        break;
                }
            } else {
                System.out.println("Geçersiz giriş. Lütfen bir sayı girin.");
                scanner.next();
            }
        }
    }
    public void showSaleDetailsByNumber(Scanner scanner) {
        System.out.println("Məlumatlarını göstərmək istədiyiniz satışın nömrəsini daxil edin: ");
        int saleNumber = scanner.nextInt();

        boolean found = false;

        for (Purchase purchase : purchases) {
            if (purchase.getId() == saleNumber) {
                found = true;
                System.out.println("Satışın nömrəsi: " + purchase.getId());
                System.out.println("Satışın mebleği: " + purchase.getTotalPrice());
                System.out.println("Məhsul sayı: " + purchase.getPurchaseItems().size());
                System.out.println("Satış tarixi: " + purchase.getCreationDate());
                System.out.println("Satış itemləri:");

                for (PurchaseItem item : purchase.getPurchaseItems()) {
                    System.out.println("  Satış item nömrəsi: " + item.getProductCode());
                    System.out.println("  Satış item sayı: " + item.getCount());
                }
                break;
            }
        }

        if (!found) {
            System.out.println("Gosterilen nömrəyə uyğun satış tapılmadı.");
        }
    }

    public void showSalesByDate(Scanner scanner) {
        System.out.println("Tarixi daxil edin (format: 'YYYY-MM-DD HH:MM:SS'): ");
        scanner.nextLine();
        String dateInput = scanner.nextLine();

        LocalDateTime selectedDate = LocalDateTime.parse(dateInput);

        boolean found = false;
        System.out.println("Verilmiş tarixdə olan satışlar:");

        for (Purchase purchase : purchases) {
            LocalDateTime purchaseDate = purchase.getCreationDate();
            if (purchaseDate.toLocalDate().isEqual(selectedDate.toLocalDate())) {
                found = true;
                System.out.println("Satışın nömrəsi: " + purchase.getId() +
                        ", Toplam mebleğ: " + purchase.getTotalPrice() +
                        ", Məhsul sayı: " + purchase.getPurchaseItems().size() +
                        ", Tarix: " + purchase.getCreationDate());
            }
        }

        if (!found) {
            System.out.println("Verilmiş tarixdə heç bir satış yoxdur.");
        }
    }

    public void showSalesByAmountRange(double minAmount, double maxAmount) {
        boolean found = false;
        System.out.println("Verilmiş mebleğ aralığına görə satışlar:");

        for (Purchase purchase : purchases) {
            if (purchase.getTotalPrice() >= minAmount && purchase.getTotalPrice() <= maxAmount) {
                found = true;
                System.out.println("Satışın nömrəsi: " + purchase.getId() +
                        ", Toplam mebleğ: " + purchase.getTotalPrice() +
                        ", Məhsul sayı: " + purchase.getPurchaseItems().size() +
                        ", Tarix: " + purchase.getCreationDate());
            }
        }

        if (!found) {
            System.out.println("Seçdiyiniz mebleğ aralığında heç bir satış yoxdur.");
        }
    }
    private void createNewSale(Scanner scanner) {
        List<PurchaseItem> purchaseItems = new ArrayList<>();
        int totalPrice = 0;

        System.out.println("How many different products do you want to add to the sale?");
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.print("Enter the product code: ");
            int code = scanner.nextInt();
            System.out.print("Enter the quantity of the product: ");
            int count = scanner.nextInt();

            Product productToAdd = getProductByCode(code);
            if (productToAdd != null && productToAdd.getCount() >= count) {
                productToAdd.setCount(productToAdd.getCount() - count);
                int priceOfProduct = (int) (productToAdd.getPrice() * count);
                totalPrice += priceOfProduct;
                PurchaseItem purchaseItem = new PurchaseItem(code, productToAdd.getName(), count);
                purchaseItems.add(purchaseItem);
            } else {
                System.out.println("Invalid product code or insufficient quantity. Sale operation aborted.");
                return;
            }
        }

        LocalDateTime creationDate = LocalDateTime.now();
        Purchase newPurchase = new Purchase(purchases.size() + 1, purchaseItems, creationDate, totalPrice);
        purchases.add(newPurchase);
        System.out.println("Sale added successfully.");

        // Show details of the last added sale
        showSaleDetails(newPurchase);
    }

    private void showSaleDetails(Purchase purchase) {
        System.out.println("Sale details:");
        System.out.println("Sale number: " + purchase.getId());
        System.out.println("Total price: " + purchase.getTotalPrice());
        System.out.println("Number of items: " + purchase.getPurchaseItems().size());
        System.out.println("Date: " + purchase.getCreationDate());
        System.out.println("Sale items:");
        for (PurchaseItem item : purchase.getPurchaseItems()) {
            System.out.println("  Product code: " + item.getProductCode());
            System.out.println("  Quantity: " + item.getCount());
        }
    }

    private Product getProductByCode(int code) {
        for (Product product : products) {
            if (product.getCode() == code) {
                return product;
            }
        }
        return null;
    }

    public void returnProductFromSale(Scanner scanner) {
        System.out.println("Satisin nomresini daxil edin: ");
        int saleNumber = scanner.nextInt();

        boolean saleFound = false;
        for (Purchase purchase : purchases) {
            if (purchase.getId() == saleNumber) {
                saleFound = true;
                List<PurchaseItem> purchaseItems = purchase.getPurchaseItems();

                System.out.println("Mehsulun kodunu daxil edin: ");
                int productCode = scanner.nextInt();

                System.out.println("Mehsuldan nece eded geri qaytarirsiz?: ");
                int returnQuantity = scanner.nextInt();

                boolean productReturned = false;
                for (PurchaseItem purchaseItem : purchaseItems) {
                    if (purchaseItem.getProductCode() == productCode) {
                        productReturned = true;
                        int currentQuantity = purchaseItem.getCount();

                        if (returnQuantity > currentQuantity) {
                            System.out.println("Error: Daha çox məhsul qaytarılmışdır, əməliyyat dayandırılır.");
                            return;
                        }

                        purchaseItem.setCount(currentQuantity - returnQuantity);
                        System.out.println(returnQuantity + " ədəd məhsul geri qaytarıldı.");
                        break;
                    }
                }

                if (!productReturned) {
                    System.out.println("Məhsul tapılmadı.");
                }

                break;
            }
        }

        if (!saleFound) {
            System.out.println("Satış tapılmadı.");
        }
    }
    public void deleteSaleByNumber(Scanner scanner) {
        System.out.println("Silmək istədiyiniz satışın nömrəsini daxil edin: ");
        int saleNumber = scanner.nextInt();

        boolean saleFound = false;
        for (int i = 0; i < purchases.size(); i++) {
            if (purchases.get(i).getId() == saleNumber) {
                purchases.remove(i);
                saleFound = true;
                System.out.println("Satış silindi.");
                break;
            }
        }

        if (!saleFound) {
            System.out.println("Gosterilen nömrəyə uyğun satış tapılmadı.");
        }
    }

    public void showAllSales() {
        if (purchases.isEmpty()) {
            System.out.println("Hələ ki, heç bir satış əlavə edilməyib.");
        } else {
            System.out.println("Bütün satışlar:");
            for (Purchase purchase : purchases) {
                System.out.println("Satışın nömrəsi: " + purchase.getId() +
                        ", Toplam mebleğ: " + purchase.getTotalPrice() +
                        ", Məhsul sayı: " + purchase.getPurchaseItems().size() +
                        ", Tarix: " + purchase.getCreationDate());
            }
        }
    }


    public void showSalesByDateRange(Scanner scanner) {
        System.out.println("Tarix aralığını daxil edin (başlanğıc tarix və bitiş tarixini formatda 'YYYY-MM-DD HH:MM:SS' kimi daxil edin): ");
        scanner.nextLine(); // Boş satır okuma
        String startDateString = scanner.nextLine();
        String endDateString = scanner.nextLine();

        LocalDateTime startDate = LocalDateTime.parse(startDateString);
        LocalDateTime endDate = LocalDateTime.parse(endDateString);

        boolean found = false;
        System.out.println("Verilmiş tarix aralığındakı satışlar:");

        for (Purchase purchase : purchases) {
            LocalDateTime purchaseDate = purchase.getCreationDate();
            if (purchaseDate.isAfter(startDate) && purchaseDate.isBefore(endDate)) {
                found = true;
                System.out.println("Satışın nömrəsi: " + purchase.getId() +
                        ", Toplam mebleğ: " + purchase.getTotalPrice() +
                        ", Məhsul sayı: " + purchase.getPurchaseItems().size() +
                        ", Tarix: " + purchase.getCreationDate());
            }
        }

        if (!found) {
            System.out.println("Verilmiş tarix aralığında heç bir satış yoxdur.");
        }
    }



    public void addProduct(Scanner scanner) {
        scanner.nextLine();
        System.out.println("Məhsulun adını daxil edin: ");
        String name = scanner.nextLine();

        System.out.println("Məhsulun qiymətini daxil edin: ");
        double price = scanner.nextDouble();

        System.out.println("Məhsulun kateqoriyasını daxil edin (FOOD, DRINK, SWEET, MEAT): ");
        scanner.nextLine();
        String category = scanner.nextLine();

        System.out.println("Məhsulun sayını daxil edin: ");
        int quantity = scanner.nextInt();

        System.out.println("Məhsulun kodunu daxil edin: ");
        int code = scanner.nextInt();

        ProductType productType = ProductType.valueOf(category.toUpperCase());
        Product newProduct = new Product(name, price, productType, quantity, code);
        products.add(newProduct);
        System.out.println("Məhsul əlavə edildi.");
    }

    public void editProduct(Scanner scanner) {
        scanner.nextLine();
        System.out.println("Düzəliş etmək istədiyiniz məhsulun kodunu daxil edin: ");
        int productCode = scanner.nextInt();

        boolean found = false;

        for (Product product : products) {
            if (product.getCode() == productCode) {
                found = true;
                System.out.println("Məhsulun adı: " + product.getName());
                System.out.println("Məhsulun yeni adını daxil edin: ");
                scanner.nextLine();
                String newName = scanner.nextLine();
                product.setName(newName);

                System.out.println("Məhsulun qiyməti: " + product.getPrice());
                System.out.println("Məhsulun yeni qiymətini daxil edin: ");
                double newPrice = scanner.nextDouble();
                product.setPrice(newPrice);

                System.out.println("Məhsula düzəliş edildi.");
                break;
            }
        }

        if (!found) {
            System.out.println("Məhsul tapılmadı.");
        }
    }
    public void deleteProductByCode(Scanner scanner) {
        System.out.println("Silmək istədiyiniz məhsulun kodunu daxil edin: ");
        int productCode = scanner.nextInt();

        boolean isRemoved = false;

        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (product.getCode() == productCode) {
                products.remove(i);
                isRemoved = true;
                System.out.println("Məhsul silindi.");
                break;
            }
        }

        if (!isRemoved) {
            System.out.println("Məhsul tapılmadı.");
        }
    }

    public void showAllProducts() {
        if (products.isEmpty()) {
            System.out.println("Hələ ki, heç bir məhsul əlavə edilməyib.");
        } else {
            System.out.println("Bütün məhsullar:");
            for (Product product : products) {
                System.out.println("Kod: " + product.getCode() +
                        ", Ad: " + product.getName() +
                        ", Qiymət: " + product.getPrice() +
                        ", Say: " + product.getCount() +
                        ", Kateqoriya: " + product.getProductType());
            }
        }
    }
    public void showProductsByCategory(ProductType category) {
        boolean found = false;
        System.out.println("Kateqoriyasına görə məhsullar:");

        for (Product product : products) {
            if (product.getProductType() == category) {
                found = true;
                System.out.println("Kod: " + product.getCode() +
                        ", Ad: " + product.getName() +
                        ", Qiymət: " + product.getPrice() +
                        ", Say: " + product.getCount() +
                        ", Kateqoriya: " + product.getProductType());
            }
        }

        if (!found) {
            System.out.println("Seçdiyiniz kateqoriyada heç bir məhsul yoxdur.");
        }
    }

    public void showProductsByPriceRange(double minPrice, double maxPrice) {
        boolean found = false;
        System.out.println("Qiymət aralığına görə məhsullar:");

        for (Product product : products) {
            if (product.getPrice() >= minPrice && product.getPrice() <= maxPrice) {
                found = true;
                System.out.println("Kod: " + product.getCode() +
                        ", Ad: " + product.getName() +
                        ", Qiymət: " + product.getPrice() +
                        ", Say: " + product.getCount() +
                        ", Kateqoriya: " + product.getProductType());
            }
        }

        if (!found) {
            System.out.println("Seçdiyiniz qiymət aralığında heç bir məhsul yoxdur.");
        }
    }

    public void searchProductByName(String searchName) {
        boolean found = false;
        System.out.println("Axtarış nəticələri:");

        for (Product product : products) {
            if (product.getName().toLowerCase().contains(searchName.toLowerCase())) {
                found = true;
                System.out.println("Kod: " + product.getCode() +
                        ", Ad: " + product.getName() +
                        ", Qiymət: " + product.getPrice() +
                        ", Say: " + product.getCount() +
                        ", Kateqoriya: " + product.getProductType());
            }
        }

        if (!found) {
            System.out.println("Axtarışa uyğun heç bir məhsul tapılmadı.");
        }
    }


}
