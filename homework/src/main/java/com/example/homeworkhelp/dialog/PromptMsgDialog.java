package com.example.homeworkhelp.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.homeworkhelp.R;
import com.example.homeworkhelp.listener.PromptMsgListener;

/**
 * @author Administrator
 * @time 2017/3/15 0015 21:08
 */

public class PromptMsgDialog extends Dialog {
    private TextView promptTitleTxt;
    private TextView promptContentTxt;
    private Button leftBtn;
    private Button rightBtn;

    private String title, content, leftTxt, rightTxt;
    private PromptMsgListener promptMsgListener;

    /**
     *
     * @param context
     * @param title
     * @param content
     * @param leftTxt
     * @param rightTxt
     * @param promptMsgListener
     */
    public PromptMsgDialog(Context context, String title, String content, String leftTxt, String rightTxt, PromptMsgListener promptMsgListener) {
        super(context);
        this.title = title;
        this.content = content;
        this.leftTxt = leftTxt;
        this.rightTxt = rightTxt;
        this.promptMsgListener = promptMsgListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prompt_msg_dialog);

        promptTitleTxt = (TextView) findViewById(R.id.promptTitleTxt);
        promptContentTxt = (TextView) findViewById(R.id.promptContentTxt);
        leftBtn = (Button) findViewById(R.id.leftBtn);
        rightBtn = (Button) findViewById(R.id.rightBtn);

        promptTitleTxt.setText(title);
        promptContentTxt.setText(content);
        leftBtn.setText(leftTxt);
        rightBtn.setText(rightTxt);

        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                promptMsgListener.isAgree(true);
            }
        });

        rightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
}
