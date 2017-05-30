public class PrimeNumber{
    public static void main(String arg[]){
        try {
            primeNumber(Integer.parseInt(arg[0]));
        } catch (Exception e){
        }
    }

    public static void primeNumber(int n) {
        boolean mark = true;
        if (n==1) return;
        for (int i=2; i<=n/2; i++){
            if(n%i==0){
                mark = false;
                break;
            }
        }

        if(mark)
            System.out.print(n + " ");

        primeNumber(n-1);
    }
}
