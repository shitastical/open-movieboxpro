package com.movieboxpro.android.utils.tool;

import com.movieboxpro.android.utils.ShellUtil;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class ShellUtils {
    private static final String LINE_SEP = System.getProperty("line.separator");

    private ShellUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static Utils.Task<CommandResult> execCmdAsync(final String command, final boolean isRooted, final Utils.Consumer<CommandResult> consumer) {
        return execCmdAsync(new String[]{command}, isRooted, true, consumer);
    }

    public static Utils.Task<CommandResult> execCmdAsync(final List<String> commands, final boolean isRooted, final Utils.Consumer<CommandResult> consumer) {
        return execCmdAsync(commands == null ? null : (String[]) commands.toArray(new String[0]), isRooted, true, consumer);
    }

    public static Utils.Task<CommandResult> execCmdAsync(final String[] commands, final boolean isRooted, final Utils.Consumer<CommandResult> consumer) {
        return execCmdAsync(commands, isRooted, true, consumer);
    }

    public static Utils.Task<CommandResult> execCmdAsync(final String command, final boolean isRooted, final boolean isNeedResultMsg, final Utils.Consumer<CommandResult> consumer) {
        return execCmdAsync(new String[]{command}, isRooted, isNeedResultMsg, consumer);
    }

    public static Utils.Task<CommandResult> execCmdAsync(final List<String> commands, final boolean isRooted, final boolean isNeedResultMsg, final Utils.Consumer<CommandResult> consumer) {
        return execCmdAsync(commands == null ? null : (String[]) commands.toArray(new String[0]), isRooted, isNeedResultMsg, consumer);
    }

    public static Utils.Task<CommandResult> execCmdAsync(final String[] commands, final boolean isRooted, final boolean isNeedResultMsg, final Utils.Consumer<CommandResult> consumer) {
        return UtilsBridge.doAsync(new Utils.Task<CommandResult>(consumer) { // from class: com.movieboxpro.android.utils.tool.ShellUtils.1
            @Override // com.movieboxpro.android.utils.tool.ThreadUtils.Task
            public CommandResult doInBackground() {
                return ShellUtils.execCmd(commands, isRooted, isNeedResultMsg);
            }
        });
    }

    public static CommandResult execCmd(final String command, final boolean isRooted) {
        return execCmd(new String[]{command}, isRooted, true);
    }

    public static CommandResult execCmd(final List<String> commands, final boolean isRooted) {
        return execCmd(commands == null ? null : (String[]) commands.toArray(new String[0]), isRooted, true);
    }

    public static CommandResult execCmd(final String[] commands, final boolean isRooted) {
        return execCmd(commands, isRooted, true);
    }

    public static CommandResult execCmd(final String command, final boolean isRooted, final boolean isNeedResultMsg) {
        return execCmd(new String[]{command}, isRooted, isNeedResultMsg);
    }

    public static CommandResult execCmd(final List<String> commands, final boolean isRooted, final boolean isNeedResultMsg) {
        return execCmd(commands == null ? null : (String[]) commands.toArray(new String[0]), isRooted, isNeedResultMsg);
    }

    /* JADX WARN: Code duplicated, block: B:103:0x013d  */
    /* JADX WARN: Code duplicated, block: B:107:0x0146  */
    /* JADX WARN: Code duplicated, block: B:108:0x0148  */
    /* JADX WARN: Code duplicated, block: B:111:0x014f  */
    /* JADX WARN: Code duplicated, block: B:131:0x0178  */
    /* JADX WARN: Code duplicated, block: B:135:0x0133 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:141:0x016e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:143:0x0129 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:147:0x0164 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:149:0x011f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:157:0x015a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:174:? A[SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v6, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r3v9 */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v13 */
    /* JADX WARN: Type inference failed for: r5v14 */
    /* JADX WARN: Type inference failed for: r5v15 */
    /* JADX WARN: Type inference failed for: r5v16, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r5v17 */
    /* JADX WARN: Type inference failed for: r5v18 */
    /* JADX WARN: Type inference failed for: r5v3, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r5v4, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r5v5 */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r5v8 */
    /* JADX WARN: Type inference failed for: r5v9 */
    /* JADX WARN: Type inference failed for: r6v0 */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v10, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r6v11 */
    /* JADX WARN: Type inference failed for: r6v12 */
    /* JADX WARN: Type inference failed for: r6v13 */
    /* JADX WARN: Type inference failed for: r6v15 */
    /* JADX WARN: Type inference failed for: r6v16 */
    /* JADX WARN: Type inference failed for: r6v17, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r6v19 */
    /* JADX WARN: Type inference failed for: r6v2, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r6v20 */
    /* JADX WARN: Type inference failed for: r6v3, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r6v4 */
    /* JADX WARN: Type inference failed for: r6v5 */
    /* JADX WARN: Type inference failed for: r6v6 */
    /* JADX WARN: Type inference failed for: r6v9 */
    public static CommandResult execCmd(String[] strArr, boolean z, boolean z2) throws Throwable {
        Process processExec;
        ?? bufferedReader;
        StringBuilder sb;
        ?? bufferedReader2;
        StringBuilder sb2;
        StringBuilder sb3;
        StringBuilder sb4;
        String string;
        ?? r6;
        String string2 = "";
        int iWaitFor = -1;
        if (strArr == null || strArr.length == 0) {
            return new CommandResult(-1, "", "");
        }
        DataOutputStream dataOutputStream = null;
        ?? r3 = 0;
        dataOutputStream = null;
        try {
            processExec = Runtime.getRuntime().exec(z ? "su" : ShellUtil.COMMAND_SH);
            try {
                DataOutputStream dataOutputStream2 = new DataOutputStream(processExec.getOutputStream());
                try {
                    try {
                        for (String str : strArr) {
                            if (str != null) {
                                dataOutputStream2.write(str.getBytes());
                                dataOutputStream2.writeBytes(LINE_SEP);
                                dataOutputStream2.flush();
                            }
                        }
                        dataOutputStream2.writeBytes("exit" + LINE_SEP);
                        dataOutputStream2.flush();
                        iWaitFor = processExec.waitFor();
                        if (z2) {
                            sb3 = new StringBuilder();
                            try {
                                sb4 = new StringBuilder();
                                try {
                                    bufferedReader = new BufferedReader(new InputStreamReader(processExec.getInputStream(), "UTF-8"));
                                    try {
                                        bufferedReader2 = new BufferedReader(new InputStreamReader(processExec.getErrorStream(), "UTF-8"));
                                        try {
                                            String line = bufferedReader.readLine();
                                            if (line != null) {
                                                sb3.append(line);
                                                while (true) {
                                                    String line2 = bufferedReader.readLine();
                                                    if (line2 == null) {
                                                        break;
                                                    }
                                                    sb3.append(LINE_SEP);
                                                    sb3.append(line2);
                                                }
                                            }
                                            String line3 = bufferedReader2.readLine();
                                            if (line3 != null) {
                                                sb4.append(line3);
                                                while (true) {
                                                    String line4 = bufferedReader2.readLine();
                                                    if (line4 == null) {
                                                        break;
                                                    }
                                                    sb4.append(LINE_SEP);
                                                    sb4.append(line4);
                                                }
                                            }
                                            r3 = bufferedReader;
                                            r6 = bufferedReader2;
                                        } catch (Exception e) {
                                            e = e;
                                            dataOutputStream = dataOutputStream2;
                                            sb = sb3;
                                            e = e;
                                            sb2 = sb4;
                                            bufferedReader = bufferedReader;
                                            bufferedReader2 = bufferedReader2;
                                            try {
                                                e.printStackTrace();
                                                if (dataOutputStream != null) {
                                                    try {
                                                        dataOutputStream.close();
                                                    } catch (IOException e2) {
                                                        e2.printStackTrace();
                                                    }
                                                }
                                                if (bufferedReader != 0) {
                                                    try {
                                                        bufferedReader.close();
                                                    } catch (IOException e3) {
                                                        e3.printStackTrace();
                                                    }
                                                }
                                                if (bufferedReader2 != 0) {
                                                    try {
                                                        bufferedReader2.close();
                                                    } catch (IOException e4) {
                                                        e4.printStackTrace();
                                                    }
                                                }
                                                if (processExec != null) {
                                                    processExec.destroy();
                                                }
                                                sb3 = sb;
                                                sb4 = sb2;
                                            } catch (Throwable th) {
                                                th = th;
                                                if (dataOutputStream != null) {
                                                    try {
                                                        dataOutputStream.close();
                                                    } catch (IOException e5) {
                                                        e5.printStackTrace();
                                                    }
                                                }
                                                if (bufferedReader != 0) {
                                                    try {
                                                        bufferedReader.close();
                                                    } catch (IOException e6) {
                                                        e6.printStackTrace();
                                                    }
                                                }
                                                if (bufferedReader2 != 0) {
                                                    try {
                                                        bufferedReader2.close();
                                                    } catch (IOException e7) {
                                                        e7.printStackTrace();
                                                    }
                                                }
                                                if (processExec != null) {
                                                    processExec.destroy();
                                                    throw th;
                                                }
                                                throw th;
                                            }
                                        } catch (Throwable th2) {
                                            th = th2;
                                            dataOutputStream = dataOutputStream2;
                                            if (dataOutputStream != null) {
                                                dataOutputStream.close();
                                            }
                                            if (bufferedReader != 0) {
                                                bufferedReader.close();
                                            }
                                            if (bufferedReader2 != 0) {
                                                bufferedReader2.close();
                                            }
                                            if (processExec != null) {
                                                processExec.destroy();
                                                throw th;
                                            }
                                            throw th;
                                        }
                                    } catch (Exception e8) {
                                        e = e8;
                                        bufferedReader2 = 0;
                                    } catch (Throwable th3) {
                                        th = th3;
                                        bufferedReader2 = 0;
                                    }
                                } catch (Exception e9) {
                                    e = e9;
                                    bufferedReader = 0;
                                    bufferedReader2 = 0;
                                }
                            } catch (Exception e10) {
                                sb2 = null;
                                bufferedReader = 0;
                                bufferedReader2 = 0;
                                dataOutputStream = dataOutputStream2;
                                sb = sb3;
                                e = e10;
                            }
                        } else {
                            sb3 = null;
                            sb4 = null;
                            r6 = 0;
                        }
                        try {
                            dataOutputStream2.close();
                        } catch (IOException e11) {
                            e11.printStackTrace();
                        }
                        if (r3 != 0) {
                            try {
                                r3.close();
                            } catch (IOException e12) {
                                e12.printStackTrace();
                            }
                        }
                        if (r6 != 0) {
                            try {
                                r6.close();
                            } catch (IOException e13) {
                                e13.printStackTrace();
                            }
                        }
                        if (processExec != null) {
                            processExec.destroy();
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        bufferedReader = 0;
                        bufferedReader2 = 0;
                    }
                } catch (Exception e14) {
                    e = e14;
                    sb = null;
                    sb2 = null;
                    bufferedReader = 0;
                    bufferedReader2 = 0;
                    dataOutputStream = dataOutputStream2;
                }
            } catch (Exception e15) {
                e = e15;
                sb = null;
                sb2 = sb;
                StringBuilder sb5 = sb2;
                bufferedReader2 = sb5;
                bufferedReader = sb5;
                e.printStackTrace();
                if (dataOutputStream != null) {
                    dataOutputStream.close();
                }
                if (bufferedReader != 0) {
                    bufferedReader.close();
                }
                if (bufferedReader2 != 0) {
                    bufferedReader2.close();
                }
                if (processExec != null) {
                    processExec.destroy();
                }
                sb3 = sb;
                sb4 = sb2;
                if (sb3 == null) {
                    string = "";
                } else {
                    string = sb3.toString();
                }
                if (sb4 != null) {
                    string2 = sb4.toString();
                }
                return new CommandResult(iWaitFor, string, string2);
            } catch (Throwable th5) {
                th = th5;
                bufferedReader = 0;
                bufferedReader2 = bufferedReader;
                if (dataOutputStream != null) {
                    dataOutputStream.close();
                }
                if (bufferedReader != 0) {
                    bufferedReader.close();
                }
                if (bufferedReader2 != 0) {
                    bufferedReader2.close();
                }
                if (processExec != null) {
                    processExec.destroy();
                    throw th;
                }
                throw th;
            }
        } catch (Exception e16) {
            e = e16;
            processExec = null;
            sb = null;
        } catch (Throwable th6) {
            th = th6;
            processExec = null;
            bufferedReader = 0;
        }
        if (sb3 == null) {
            string = "";
        } else {
            string = sb3.toString();
        }
        if (sb4 != null) {
            string2 = sb4.toString();
        }
        return new CommandResult(iWaitFor, string, string2);
    }

    public static class CommandResult {
        public String errorMsg;
        public int result;
        public String successMsg;

        public CommandResult(final int result, final String successMsg, final String errorMsg) {
            this.result = result;
            this.successMsg = successMsg;
            this.errorMsg = errorMsg;
        }

        public String toString() {
            return "result: " + this.result + "\nsuccessMsg: " + this.successMsg + "\nerrorMsg: " + this.errorMsg;
        }
    }
}
