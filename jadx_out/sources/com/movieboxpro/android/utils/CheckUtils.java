package com.movieboxpro.android.utils;

import android.text.TextUtils;
import android.util.Patterns;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes5.dex */
public class CheckUtils {
    public static final String PATTENR_INVITE_CODE = "(<stoneread>)(\\d{1,})(</stoneread>)";
    private static final String PATTERN_PHONE = "[1][3578]\\d{9}";
    public static final String PATTERN_URL = "(([a-zA-Z0-9\\._-]+\\.[a-zA-Z]{2,6})|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,4})*(/[a-zA-Z0-9\\&%_\\./-~-]*)?";
    public static final String PATTERN_URL_FULL = "((http|ftp|https)://)(([a-zA-Z0-9\\._-]+\\.[a-zA-Z]{2,6})|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,4})*(/[a-zA-Z0-9\\&%_\\./-~-]*)?";
    private static final String PATTERN_USERNAME = "[a-zA-Z0-9_-]{6,16}";

    public static boolean checkPhone(String phone) {
        return !TextUtils.isEmpty(phone) && phone.matches(PATTERN_PHONE);
    }

    public static boolean checkUsername(String username) {
        return !TextUtils.isEmpty(username) && username.matches(PATTERN_USERNAME);
    }

    public static boolean checkEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean hasUrl(String text) {
        return Pattern.compile(PATTERN_URL_FULL).matcher(text).find();
    }

    public static boolean checkFileExists(String path, String md5) {
        if (path != null) {
            File file = new File(path);
            if (file.exists() && file.isFile()) {
                try {
                    return HexDump.toHexString(MD5Util.md5(new FileInputStream(file))).equalsIgnoreCase(md5);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e2) {
                    e2.printStackTrace();
                } catch (NoSuchAlgorithmException e3) {
                    e3.printStackTrace();
                }
            }
        }
        return false;
    }

    public static boolean checkInviteCode(String inviteCode) {
        return inviteCode != null && inviteCode.matches(PATTENR_INVITE_CODE);
    }

    public static boolean checkFileMd5(File file, String md5) throws Throwable {
        boolean z = false;
        FileInputStream fileInputStream = null;
        try {
            try {
                try {
                    FileInputStream fileInputStream2 = new FileInputStream(file);
                    try {
                        String hexString = HexDump.toHexString(MD5Util.md5(fileInputStream2));
                        if (md5 != null && md5.equalsIgnoreCase(hexString)) {
                            z = true;
                        }
                        try {
                            fileInputStream2.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return z;
                    } catch (IOException e2) {
                        e = e2;
                        fileInputStream = fileInputStream2;
                        e.printStackTrace();
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        return false;
                    } catch (NoSuchAlgorithmException e3) {
                        e = e3;
                        fileInputStream = fileInputStream2;
                        e.printStackTrace();
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        return false;
                    } catch (Throwable th) {
                        th = th;
                        fileInputStream = fileInputStream2;
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (IOException e5) {
                e = e5;
            } catch (NoSuchAlgorithmException e6) {
                e = e6;
            }
        } catch (IOException e7) {
            e7.printStackTrace();
        }
    }
}
