import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) throws InterruptedException
    {
        Gson gson = new Gson();
        WebsocketClient websocketClient = new WebsocketClient("wss://iotnet.cibicom.dk/app?token=vnoUBwAAABFpb3RuZXQuY2liaWNvbS5ka54Zx4fqYp5yzAQtnGzDDUw=");
        while(true)
        {
            DataFormat dataFormat = new DataFormat(1234, 250, 5000);
            DownLinkMessage downLinkMessage = new DownLinkMessage(
                "tx",
                "0004A30B00251001",
                2,
                false,
                dataFormat.toBytes());
            System.out.println(gson.toJson(downLinkMessage, DownLinkMessage.class));
            websocketClient.sendDownLink(gson.toJson(downLinkMessage, DownLinkMessage.class));
            Thread.sleep(180000);
        }
    }
}
