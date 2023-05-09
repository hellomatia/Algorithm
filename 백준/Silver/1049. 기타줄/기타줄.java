import java.io.*;
import java.util.StringTokenizer;

class GuitarStringPrice{
    int packagePrice;
    int individualPrice;
    GuitarStringPrice(int packagePrice, int individualPrice) {
        this.packagePrice = packagePrice;
        this.individualPrice = individualPrice;
    }

}

public class Main {
    public void solution() throws IOException{

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        int brokenStringCount = Integer.parseInt(st.nextToken());
        int guitarStringBrandCount = Integer.parseInt(st.nextToken());

        GuitarStringPrice[] guitarStringPrices = new GuitarStringPrice[guitarStringBrandCount];

        int minPackagePrice = Integer.MAX_VALUE;
        int minIndividualPrice = Integer.MAX_VALUE;

        for(int i=0; i<guitarStringBrandCount; i++){
            st = new StringTokenizer(bf.readLine(), " ");

            int packagePrice = Integer.parseInt(st.nextToken());
            int individualPrice = Integer.parseInt(st.nextToken());

            guitarStringPrices[i] = new GuitarStringPrice(packagePrice, individualPrice);

            if(minPackagePrice>guitarStringPrices[i].packagePrice){
                minPackagePrice = guitarStringPrices[i].packagePrice;
            }

            if(minIndividualPrice>guitarStringPrices[i].individualPrice){
                minIndividualPrice = guitarStringPrices[i].individualPrice;
            }
        }

        int packageCount = brokenStringCount/6;
        int minTotalPrice = 0;

        if(packageCount==0){
            minTotalPrice = Math.min(minPackagePrice,(minIndividualPrice*brokenStringCount));
        }
        else if((float)minPackagePrice/6>=minIndividualPrice){
            minTotalPrice = minIndividualPrice*brokenStringCount;
        }
        else {
            minTotalPrice = Math.min(minPackagePrice*(packageCount+1), (minPackagePrice*packageCount)+(minIndividualPrice*(brokenStringCount%6)));
        }


        bw.write(String.valueOf(minTotalPrice));

        bw.flush();
        bw.close();

    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}

