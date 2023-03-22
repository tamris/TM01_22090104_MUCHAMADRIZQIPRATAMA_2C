package View;

import Model.ResponseModel;
import Network.ConnectURI;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;

public class myGUI {
    private JPanel mainPanel;
    private JButton minimize;
    private JButton submiteButton;
    private JTextField msgText;
    private JTextField statusText;
    private JTextField commentText;
    private JButton exitButton;

    public JPanel getMainPanel(){
        return mainPanel;
    }

    public JButton getMinimize (){
        return minimize;
    }

    public myGUI() {
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        submiteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                msgText.setText("");
                statusText.setText("");
                commentText.setText("");
                try{
                    ConnectURI connection = new ConnectURI();
                    URL myAddress = connection.buildURL("http://harber.mimoapps.xyz/api/getaccess.php");
                    String response = connection.getResponseFromHttpUrl(myAddress);

                    JSONArray responseJSON = new JSONArray(response);
                    ArrayList<ResponseModel> responseModel = new ArrayList<>();
                    for (int i = 0; i < responseJSON.length(); i++) {
                        ResponseModel resModel = new ResponseModel();
                        JSONObject myJSONObject = responseJSON.getJSONObject(i);
                        resModel.setMsg(myJSONObject.getString("message"));
                        resModel.setStatus(myJSONObject.getString("status"));
                        resModel.setComment(myJSONObject.getString("comment"));
                        responseModel.add(resModel);
                    }
                    for(ResponseModel respond : responseModel){
                        msgText.setText(respond.getMsg());
                        statusText.setText(respond.getStatus());
                        commentText.setText(respond.getComment());
                    }
                }catch (Exception ex){
                    System.out.println(ex);
                }
            }
        });
    }
}
