import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

class Zcoin implements Serializable {
    private HashMap<String, User> Zuser_details = new HashMap<>();
    private HashMap<String, User> user_details = new HashMap<>();
    public ArrayList<String> emailID = new ArrayList<>();
    public ArrayList<Integer> ZID = new ArrayList<>();
    public ArrayList<Integer> UID = new ArrayList<>();
    private int Zcoin = 2;

    public int getZcoin() {
        return Zcoin;
    }

    public void setZcoin(int zcoin) {
        Zcoin = zcoin;
    }

    public HashMap<String, User> getZuser_details() {
        return Zuser_details;
    }

    public void setZuser_details(HashMap<String, User> zuser_details) {
        Zuser_details = zuser_details;
    }

    public HashMap<String, User> getUser_details() {
        return user_details;
    }

    public void setUser_details(HashMap<String, User> user_details) {
        this.user_details = user_details;
    }

    public static boolean autenticate(String a, String b, HashMap<String, User> user) {
        if (user.containsKey(a)) {
            String pass = user.get(a).getPassword();
            if (b.equals(pass)) {
                return true;
            } else {
                System.err.println("WRONG Password....!!!");
                return false;
            }
        }
        System.err.println("User email not found....!!!");
        return false;

    }

    public static boolean validateEmail(String email) {
        return (Pattern.matches("[a-z0-9]+[[\\.]*[a-z0-9]+]*" + "@zmail.com", email));
    }

    public static boolean validatePass(String pass) {
        return (Pattern.matches("[[a-z]{2,}]+[[A-Z]{2,}]+[[0-9]{2,}]+[[\\!#%\\?>\\$<\\&\\*]{2,}]+", pass));
    }

}

class User implements Serializable {
    private String Name;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    private String Password;

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    private String phone_number;

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    private int HID;

    public int getHID() {
        return HID;
    }

    public void setHID(int hID) {
        HID = hID;
    }

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private int RC;

    public int getRC() {
        return RC;
    }

    public void setRC(int rC) {
        RC = rC;
    }

    private int ZC;

    public int getZC() {
        return ZC;
    }

    public void setZC(int zC) {
        ZC = zC;
    }

    private ArrayList<String> Transaction_history = new ArrayList<>();

    public ArrayList<String> getTransaction_history() {
        return Transaction_history;
    }

    public void setTransaction_history(ArrayList<String> transaction_history) {
        Transaction_history = transaction_history;
    }

    User(String email, String pass, String phone_number, String name, int HID, int rc) {
        this.email = email;
        this.Name = name;
        this.Password = pass;
        this.phone_number = phone_number;
        this.HID = HID;
        this.RC = rc;
    }

}

class Main {
    public static void main(String[] args) throws Exception {
        File file = new File("ZcoinDB.txt");
        Zcoin zc = new Zcoin();
        try {
            if (!file.exists()) {
                file.createNewFile();
            } else {
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                Zcoin read = (Zcoin) ois.readObject();
                HashMap<String, User> user = read.getUser_details();
                HashMap<String, User> zuser = read.getZuser_details();
                zc.setUser_details(user);
                zc.setZuser_details(zuser);
                zc.ZID = read.ZID;
                zc.emailID = read.emailID;
                zc.UID = read.UID;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        int zid = 1;
        Scanner sc = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("-------------------------------------------------------------------------");
            System.out.println("1.ZE \n2.USER \n3.EXIT");
            System.out.println("-------------------------------------------------------------------------");
            int n = sc.nextInt();
            sc.nextLine();
            switch (n) {
                case 1: {
                    System.out.println("-------------------------------------------------------------------------");
                    System.out.println("AGENT Login");
                    System.out.println("-------------------------------------------------------------------------");
                    System.out.print("Enter the email : ");
                    String zemail = sc.nextLine();
                    System.out.print("Enter password : ");
                    String zpass = sc.nextLine();
                    if (!(zemail.equals("kiranraja.036@zmail.com") && zpass.equals("kiran36"))) {
                        System.out.println("you are not ZAGENT");
                        break;
                    } else {
                        boolean loop1 = true;
                        while (loop1) {
                            System.out.println(
                                    "---------------------------Welcome AGENT---------------------------------");
                            System.out.println(
                                    "1.Show transaction \n2.Show Users \n3.Show User Request \n4.Set Zcoin value \n5.LogOut");
                            System.out.println(
                                    "-------------------------------------------------------------------------");
                            int k = sc.nextInt();
                            sc.nextLine();
                            switch (k) {
                                case 1: {
                                    System.out.println("Enter the ZID : ");
                                    int z_id = sc.nextInt();
                                    sc.nextLine();
                                    if (zc.ZID.contains(z_id)) {
                                        String email = zc.emailID.get(z_id - 1);
                                        User u = zc.getUser_details().get(email);
                                        System.out.println("click ENTER to see Transaction history");
                                        if (sc.nextLine().length() == 0) {
                                            for (String i : u.getTransaction_history()) {
                                                System.out.println(i);
                                            }
                                        } else {
                                            break;
                                        }
                                        break;
                                    } else {
                                        System.out.println("there is no such zid found");
                                        break;
                                    }

                                }
                                case 2: {
                                    System.out.println("-------------------------------------------------------");
                                    System.out.println("                  USER MAILIDS");
                                    System.out.println("-------------------------------------------------------");
                                    int j = 1;
                                    for (String i : zc.getZuser_details().keySet()) {
                                        System.out.println(String.valueOf(j) + "." + i);
                                        j += 1;
                                    }
                                    System.out.println("click ENTER to exit");
                                    if (sc.nextLine().length() == 0) {
                                        break;
                                    }
                                }
                                case 3: {
                                    System.out.println("-------------------------------------------------------");
                                    System.out.println("                  USER Request");
                                    System.out.println("-------------------------------------------------------");
                                    int j = 1;

                                    for (String i : zc.getUser_details().keySet()) {
                                        System.out.println(String.valueOf(j) + "." + i);
                                        j += 1;
                                    }
                                    System.out.println(
                                            "-------------------------------------------------------------------------");
                                    while (true) {
                                        System.out.println("1.accept request \n2.reject request \n3.exit");
                                        int p = sc.nextInt();
                                        sc.nextLine();
                                        if (p == 2) {
                                            System.out.println("Enter number to reject");
                                            int s = sc.nextInt();
                                            if (s < j) {
                                                String email = zc.emailID.get(s - 1);
                                                zc.getUser_details().remove(email);
                                                zc.emailID.remove(email);
                                                zc.UID.remove(s - 1);
                                                j -= 1;
                                            }
                                        } else if (p == 1) {
                                            System.out.println("Enter number to Accept ");
                                            int s = sc.nextInt();
                                            sc.nextLine();
                                            if (s < j) {
                                                String email = zc.emailID.get(s - 1);
                                                HashMap<String, User> hm = zc.getZuser_details();
                                                hm.put(email, zc.getUser_details().get(email));
                                                zc.setZuser_details(hm);
                                                zc.emailID.remove(email);
                                                zc.UID.remove(s - 1);
                                                zc.ZID.add(zid++);
                                                j -= 1;
                                            }
                                        } else {
                                            break;
                                        }
                                    }

                                    break;
                                }
                                case 4: {
                                    System.out.println("-------------------------------------------------------");
                                    System.out.println(
                                            "                  Set Zcoin Value    current value :" + zc.getZcoin());
                                    System.out.println("-------------------------------------------------------");
                                    System.out.println("Enter the value ");
                                    int s = sc.nextInt();
                                    zc.setZcoin(s);
                                    System.out.println("Zcoin value set Successfully");

                                    break;
                                }

                                case 5: {
                                    loop1 = false;
                                    break;
                                }

                            }
                        }
                    }
                    break;
                }
                case 2: {

                    System.out.println("-------------------------------------------------------------------------");
                    System.out.println("                                User");
                    System.out.println("-------------------------------------------------------------------------");
                    System.out.println("1.REGISTER USER \n2.LOGIN USER \n3.Exit");
                    int s = sc.nextInt();
                    sc.nextLine();
                    switch (s) {
                        case 1: {
                            System.out.println(
                                    "-------------------------------------------------------------------------");
                            System.out.println("                                User register");
                            System.out.println(
                                    "-------------------------------------------------------------------------");
                            System.out.print("Enter Full name : ");
                            String name = sc.nextLine();
                            System.out.print("Enter email id : ");
                            String email = sc.nextLine();
                            while (!zc.validateEmail(email)) {
                                System.out.println("enter valid email id [ex:kiran.036@zmail.com]");
                                System.out.print("Enter email id : ");
                                email = sc.nextLine();
                            }
                            System.out.print("type password : ");
                            String pass = sc.nextLine();
                            while (!zc.validatePass(pass)) {
                                System.out.println(
                                        "enter valid PassWord contains atleast 2caps 2small 2digit 2Symbols and not same as name ");
                                System.out.print("type password : ");
                                pass = sc.nextLine();
                            }
                            System.out.print("enter phone Number ");
                            String phone_number = sc.nextLine();
                            System.out.print("Enter HID : ");
                            int HID = sc.nextInt();
                            sc.nextLine();
                            if (zc.UID.contains(HID)) {
                                System.out.print("HID already present");
                            }
                            System.out.println("Enter amount to deposit : ");
                            int rc = sc.nextInt();
                            sc.nextLine();
                            User user = new User(email, pass, phone_number, name, HID, rc);
                            HashMap<String, User> hm = zc.getUser_details();
                            hm.put(email, user);
                            zc.setUser_details(hm);
                            zc.emailID.add(email);
                            zc.UID.add(HID);
                            System.out.println("User registered succefully");
                            break;
                        }
                        case 2: {
                            System.out.println(
                                    "-------------------------------------------------------------------------");
                            System.out.println("                                User login");
                            System.out.println(
                                    "-------------------------------------------------------------------------");
                            System.out.print("Enter email : ");
                            String email = sc.nextLine();
                            System.out.print("Enter passWord : ");
                            String pass = sc.nextLine();
                            boolean key;
                            if (key = zc.autenticate(email, pass, zc.getZuser_details())) {
                                User cUser = zc.getZuser_details().get(email);
                                while (key) {
                                    System.out.println(
                                            "-------------------------------------------------------------------------");
                                    System.out.println(
                                            "                                                            rc-balance :"
                                                    + cUser.getRC());
                                    System.out.println(
                                            "                                                            zc-balance :"
                                                    + cUser.getZC());
                                    System.out.println(
                                            "1.deposit real cash \n2.withdraw cash \n3.buy ZCoins \n4.sell Zcoins \n5.transction History \n6.Transfer Zcoins \n7.Logout");
                                    System.out.println(
                                            "-------------------------------------------------------------------------");
                                    System.out.println("enter number : ");
                                    int p = sc.nextInt();
                                    sc.nextLine();
                                    switch (p) {
                                        case 1: {
                                            System.out.println("Enter amount to deposit ");
                                            int amount = sc.nextInt();
                                            cUser.setRC(cUser.getRC() + amount);
                                            cUser.getTransaction_history().add("amount deposit of " + amount);
                                            break;
                                        }
                                        case 2: {
                                            System.out.println("Enter amount to withdraw ");
                                            int amount = sc.nextInt();
                                            if (amount > cUser.getRC()) {
                                                System.out.println("Insufficient balance");
                                            } else {
                                                cUser.setRC(cUser.getRC() - amount);
                                                cUser.getTransaction_history().add("amount withdrawal of " + amount);
                                            }
                                            break;
                                        }
                                        case 3: {
                                            System.out.println("1rc = " + zc.getZcoin() + "zcoins");
                                            System.out.println("Enter no of rc ");
                                            int amount = sc.nextInt();
                                            if (amount > cUser.getRC()) {
                                                System.out.println("Insufficient balance");
                                            } else {
                                                cUser.setRC(cUser.getRC() - amount);
                                                cUser.setZC(amount * zc.getZcoin());
                                                cUser.getTransaction_history()
                                                        .add("bought Zcoins for " + amount + " RC");
                                            }
                                            break;
                                        }
                                        case 4: {
                                            System.out.println("1zcoins = " + zc.getZcoin() + "rc");
                                            System.out.println("Enter no of zc to sell ");
                                            int amount = sc.nextInt();
                                            if (amount > cUser.getZC()) {
                                                System.out.println("Insufficient zcoins balance");
                                            } else {
                                                cUser.setRC(cUser.getRC() + (amount * zc.getZcoin()));
                                                cUser.setZC(cUser.getZC() - amount);
                                                cUser.getTransaction_history().add("sold " + amount + " zcoins");
                                            }
                                            break;
                                        }

                                        case 5: {
                                            System.out.println(
                                                    "-------------------------------------------------------------------------");
                                            System.out.println("                                Transaction History");
                                            System.out.println(
                                                    "-------------------------------------------------------------------------");
                                            int j = 1;
                                            for (String i : cUser.getTransaction_history()) {
                                                System.out.println(j + ". " + i);
                                                j += 1;
                                            }
                                            break;
                                        }

                                        case 6: {
                                            System.out.print("Enter email id of user you need transfer zcoins : ");
                                            String str = sc.nextLine();
                                            if (zc.getZuser_details().containsKey(str)) {
                                                User user = zc.getZuser_details().get(str);
                                                System.out.print("Enter no of zcoins to transfer :");
                                                int amount = sc.nextInt();
                                                if (amount > cUser.getZC()) {
                                                    System.out.println("Insufficient zcoins balance");
                                                } else {
                                                    user.setZC(user.getZC() + amount);
                                                    cUser.setZC(cUser.getZC() - amount);
                                                    cUser.getTransaction_history()
                                                            .add("transfered" + amount + " zcoins to" + user.getName());
                                                    user.getTransaction_history()
                                                            .add("got " + amount + " zcoins from " + cUser.getName());
                                                }
                                                break;
                                            }
                                        }
                                        case 7: {
                                            key = false;
                                            break;
                                        }
                                    }
                                }

                            } else {
                                System.out.println("sorry..! you are not an Zuser");
                                break;
                            }
                            break;
                        }
                    }

                    break;
                }
                case 3: {
                    loop = false;
                    FileOutputStream fos = new FileOutputStream(file);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(zc);
                    oos.flush();
                    break;
                }
            }
        }
    }
}