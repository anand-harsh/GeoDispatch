package com.geodispatch.app.services;

public interface EmailSenderService {
  void sendEmail(String paramString1, String paramString2, String paramString3);

  void sendEmail(String[] paramArrayOfString, String paramString1, String paramString2);
}
