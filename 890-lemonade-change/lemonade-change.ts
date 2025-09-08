function lemonadeChange(bills: number[]): boolean {
    let c1=0,c2=0;
    for(let n of bills){
        if(n==5){
            c1++;
        }else if(n==10){
            c2++;
            if(c1==0){ return false;}
            else {
                c1--;
            }
        }else{
            if(c1>0 && c2>0) {
                c1--;
                c2--;
            }else if (c1>=3){
                c1=c1-3;
            }else {
                return false;
            }
        }
    }
    return true;
};