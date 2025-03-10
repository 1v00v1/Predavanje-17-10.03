public class test {
    public static void main(String[] args) {
        String s ="C:\\Users\\goran\\Documents\\Java-Algebra\\original.pdf";
        int p = s.lastIndexOf("\\");
        String a = s.substring(0,p+1);
        System.out.println(a);

    }
}
