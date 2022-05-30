import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) throws InterruptedException
    {
        Gson gson = new Gson();
        WebsocketClient websocketClient = new WebsocketClient("wss://iotnet.cibicom.dk/app?token=vnoUBwAAABFpb3RuZXQuY2liaWNvbS5ka54Zx4fqYp5yzAQtnGzDDUw=");
        while(true)
        {
            DataFormat dataFormat = new DataFormat(0x2da, 0x13c, 0xfc);
            DownLinkMessage downLinkMessage = new DownLinkMessage(
                "tx",
                "0004A30B00251001",
                2,
                true,
                dataFormat.toBytes());
            websocketClient.sendDownLink(gson.toJson(downLinkMessage, DownLinkMessage.class));
            Thread.sleep(30000);
        }
    }
}
