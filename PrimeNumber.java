public class PrimeNumber{
    public static void main(String arg[]){
        PrimeNumber p = new PrimeNumber();
        try {
            p.primeNumber(Integer.parseInt(arg[0]));
        } catch (Exception e){
        }
    }

    void primeNumber(int n) {
        boolean mark = true;
        if (n==1) return;
        // for (int i=2; i<=n/2; i++){
        for (int i=2; i<=Math.sqrt(n); i++){
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
