package com.example.myapplication2.chatBot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication2.R;
import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;
import com.ibm.watson.developer_cloud.http.ServiceCallback;

import java.util.HashMap;
import java.util.Map;

public class Bot extends AppCompatActivity {
    private final String TAG = "MainActivity";
    private EditText input;
    private Button translate;
    private TextView translatedText;
    private Button conv;
    private static ConversationService conversationService;
    private Handler handler = new Handler();
    public ListView msgView;
    public ArrayAdapter <String> msgList;
    Map context = new HashMap();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bot);
        conversationService = initConversationService();
        final String inputWorkspaceId="108fc630-1802-45c4-bc8e-707ebee7532d";
        msgView = (ListView) findViewById(R.id.listView);
        msgList = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        msgView.setAdapter(msgList);
        input = (EditText) findViewById(R.id.input);
        conv = (Button) findViewById(R.id.conv_button);
        MessageResponse response = null;
        conversationAPI(String.valueOf(input.getText()), context, inputWorkspaceId);

        conv.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //pressing the [Send] button passes the text to the WCS conversation service
                MessageResponse response = null;
                conversationAPI(String.valueOf(input.getText()), context, inputWorkspaceId);
            }
        });


    }

    public void conversationAPI(final String input, Map context, String workspaceId) {

        //conversationService
        MessageRequest newMessage = new MessageRequest.Builder()
                .inputText(input).context(context).build();

        //cannot use the following as it will attempt to run on the UI thread and crash
//    MessageResponse response = conversationService.message(workspaceId, newMessage).execute();

        //use the following so it runs on own async thread
        //then when get a response it calls displayMsg that will update the UI
        conversationService.message(workspaceId, newMessage).enqueue(new ServiceCallback<MessageResponse>() {
            @Override
            public void onResponse(MessageResponse response) {
                //output to system log output, just for verification/checking
                System.out.println(response);
                displayMsg(response);
            }
            @Override
            public void onFailure(Exception e) {
                showError(e);
            }
        });

    };

    private void showError(final Exception e) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(Bot.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        });
    }

    private ConversationService initConversationService() {
        ConversationService service = new ConversationService(ConversationService.VERSION_DATE_2016_07_11);
        service.setUsernameAndPassword("apikey",getString(R.string.assistant_api));
        service.setEndPoint(getString(R.string.assistant_url));
        return service;
    }

    public void displayMsg(MessageResponse msg)
    {
        final MessageResponse mssg=msg;
        handler.post(new Runnable() {

            @Override
            public void run() {
                Log.i("info",mssg.getText().toString());
                String text = mssg.getText().get(0);
                msgList.add(text);
                msgView.setAdapter(msgList);
                msgView.smoothScrollToPosition(msgList.getCount() - 1);
                context = mssg.getContext();

            }

        });

    };

}
