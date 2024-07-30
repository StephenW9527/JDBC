public class Video {

    private int videoID;
    private String videoName;
    private int price;

    public Video(int videoID, String videoName, int price){
        this.videoID = videoID;
        this.videoName = videoName;
        this.price = price;
    }


    @Override
    public String toString(){
        return "VideoID: "+videoID + ",  VideoName: " + videoName + ", Price: " + price;
    }
}
