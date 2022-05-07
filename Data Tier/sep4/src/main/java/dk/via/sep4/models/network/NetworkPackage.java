package dk.via.sep4.models.network;

/**
 * @author $(Alina Chelmus)
 */
public class NetworkPackage
{
  private final NetworkType type;
  private Object object;

  public NetworkPackage(NetworkType type, Object object) {
    this.type = type;
    this.object = object;
  }

  public NetworkType getType() {
    return type;
  }

  public Object getObject() {
    return object;
  }

  public void setObject(Object object) {
    this.object = object;
  }




}
