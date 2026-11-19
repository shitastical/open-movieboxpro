package com.movieboxpro.android.view.fragment.account;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.asm.Opcodes;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.model.user.UserModel;
import com.movieboxpro.android.utils.PrefsUtils;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: compiled from: AccountManagerFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.movieboxpro.android.view.fragment.account.AccountManagerFragment$initListener$2$1$1", f = "AccountManagerFragment.kt", i = {0, 0, 0}, l = {Opcodes.IF_ICMPNE}, m = "invokeSuspend", n = {"json", "list", FirebaseAnalytics.Param.INDEX}, s = {"L$0", "L$1", "I$0"})
final class AccountManagerFragment$initListener$2$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ UserModel.UserData $item;
    int I$0;
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ AccountManagerFragment this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AccountManagerFragment$initListener$2$1$1(UserModel.UserData userData, AccountManagerFragment accountManagerFragment, Continuation<? super AccountManagerFragment$initListener$2$1$1> continuation) {
        super(2, continuation);
        this.$item = userData;
        this.this$0 = accountManagerFragment;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AccountManagerFragment$initListener$2$1$1(this.$item, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AccountManagerFragment$initListener$2$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            String string = PrefsUtils.getInstance().getString(Constant.Prefs.ACCOUNT_LIST, "");
            Intrinsics.checkNotNull(string);
            if (!StringsKt.isBlank(string)) {
                List array = JSONObject.parseArray(string, UserModel.UserData.class);
                Intrinsics.checkNotNull(array);
                UserModel.UserData userData = this.$item;
                Iterator it = array.iterator();
                int i2 = 0;
                while (true) {
                    if (!it.hasNext()) {
                        i2 = -1;
                        break;
                    }
                    if (Intrinsics.areEqual(((UserModel.UserData) it.next()).uid, userData.uid)) {
                        break;
                    }
                    i2++;
                }
                if (i2 != -1) {
                    array.remove(i2);
                }
                PrefsUtils.getInstance().putString(Constant.Prefs.ACCOUNT_LIST, AccountListHelperKt.serializeAccountList(array));
                this.L$0 = SpillingKt.nullOutSpilledVariable(string);
                this.L$1 = SpillingKt.nullOutSpilledVariable(array);
                this.I$0 = i2;
                this.label = 1;
                if (BuildersKt.withContext(Dispatchers.getMain(), new AnonymousClass1(this.this$0, null), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.movieboxpro.android.view.fragment.account.AccountManagerFragment$initListener$2$1$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: AccountManagerFragment.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.movieboxpro.android.view.fragment.account.AccountManagerFragment$initListener$2$1$1$1", f = "AccountManagerFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        final /* synthetic */ AccountManagerFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(AccountManagerFragment accountManagerFragment, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = accountManagerFragment;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                this.this$0.getAccountList();
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
