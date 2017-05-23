package mocktests;

class Bank {


    int convert(int amount, String inCurr, String outCurr) {
        //+-20%
        //exception
        int rate = 100;
        int res;
        int rand_num = (int)((10 + (int)Math.random() * 100) * 0.20);
        int rand_pick = 0 + (int) (Math.random() * 1);
        if(rand_pick == 0)
            res = amount * (rate + rand_num);
        else
            res = amount * (rate - rand_num);
        return res;
    }
}
