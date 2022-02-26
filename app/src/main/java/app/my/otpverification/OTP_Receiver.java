package app.my.otpverification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;

import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Status;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OTP_Receiver extends BroadcastReceiver {

    private OtpReceiverListener otpReceiverListener;

    public OTP_Receiver() {
    }

    public void initListener(OtpReceiverListener otpReceiverListener) {
        this.otpReceiverListener = otpReceiverListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        SmsMessage[] smsMessages= Telephony.Sms.Intents.getMessagesFromIntent(intent);
        for (SmsMessage msg: smsMessages){
            String msgBody= msg.getMessageBody();
            String getOTP= msgBody.split(" ")[0];
            this.otpReceiverListener.onOtpSuccess(getOTP);
        }
    }

    public interface OtpReceiverListener {
        void onOtpSuccess(String otp);

        void onOtpTimeout();
    }
}
