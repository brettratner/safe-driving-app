// ITelephony.aidl
package brettratner.com.safedriving;


interface ITelephony {

  boolean endCall();

  void answerRingingCall();

  void silenceRinger();
}