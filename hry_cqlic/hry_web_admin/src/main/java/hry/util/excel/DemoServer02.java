package hry.util.excel;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class DemoServer02 {
    String data = "{\"status\":200,\"message\":\"成功\",\"timestamp\":\"2021-09-26 11:41:02\",\"data\":[{\"children\":[{\"children\":[{\"children\":[{\"name\":\"机房\"},{\"name\":\"设备\"}],\"name\":\"物理资源\",\"id\":111},{\"children\":[{\"name\":\"端口组\"},{\"name\":\"架构管理\"}],\"name\":\"逻辑资源\",\"id\":112},{\"name\":\"虚拟资源\"}],\"name\":\"资产资源信息\",\"id\":11},{\"children\":[{\"name\":\"网络事件/变更\"},{\"name\":\"网络质量\"},{\"name\":\"流量分析\"},{\"name\":\"流量采集\"},{\"name\":\"设备状态\"}],\"name\":\"网络状态信息\",\"id\":12}],\"name\":\"网工\",\"id\":1},{\"children\":[{\"children\":[{\"children\":[{\"name\":\"光传输资源树\"},{\"name\":\"传输外线光纤\"}],\"name\":\"物理资源\"},{\"children\":[{\"name\":\"光传输逻辑资源\"}],\"name\":\"逻辑\"}],\"name\":\"资产资源信息\"},{\"children\":[{\"name\":\"传输性能\"}],\"name\":\"网络状态信息\"}],\"name\":\"传输\",\"id\":2},{\"children\":[{\"name\":\"资产资源信息\"},{\"children\":[{\"name\":\"流量分析\"},{\"name\":\"流量采集\"}],\"name\":\"网络状态信息\"}],\"name\":\"架构\",\"id\":3}]}";
    String msg = "{\"status\":200,\"message\":\"成功\",\"timestamp\":\"2021-09-26 11:50:01\",\"data\":[{\"id\":130,\"name\":\"网工.资产资源信息.物理资源\"},{\"id\":131,\"name\":\"网工.资产资源信息.逻辑资源\"}]}";
    public static void main(String[] args) {

    }

    private static String checked(String msg, String data) {
        JSONObject objData = JSON.parseObject(data);
        JSONObject objMsg = JSON.parseObject(msg);
        JSONArray objDataArray = JSONArray.parseArray(objData.get("data").toString());
        JSONArray objMsgArray = JSONArray.parseArray(objMsg.get("data").toString());

        return null;
    }


}
