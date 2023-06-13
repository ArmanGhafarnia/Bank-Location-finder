public class Neighbourhood {
    String name;
    double x_min,y_min,x_max,y_max;
    public Neighbourhood(double x_min,double y_min,double x_max, double y_max,String name){
        this.name=name;
        this.x_min=x_min;
        this.y_min=y_min;
        this.x_max=x_max;
        this.y_max=y_max;
    }
}
