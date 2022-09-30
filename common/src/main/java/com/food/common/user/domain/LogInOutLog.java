package com.food.common.user.domain;

import com.food.common.basetime.BaseTimeEntity;
import com.food.common.user.enumeration.AccountType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Table(name = "tb_log_in_out_log")
@Entity
public class LogInOutLog extends BaseTimeEntity {
    @EmbeddedId
    private LogInOutLogPK logPK;

    public static LogInOutLog createLoginLog(Long accountId, AccountType type) {
        LogInOutLog log = new LogInOutLog();
        log.logPK = new LogInOutLogPK(accountId, type, LogType.LOGIN);
        return log;
    }

    public static LogInOutLog createLogoutLog(Long accountId, AccountType type) {
        LogInOutLog log = new LogInOutLog();
        log.logPK = new LogInOutLogPK(accountId, type, LogType.LOGOUT);
        return log;
    }

    @Getter
    @Embeddable
    @EqualsAndHashCode
    @NoArgsConstructor(access = PROTECTED)
    public static class LogInOutLogPK implements Serializable {
        private Long accountId;
        private AccountType accountType;
        private LogType logType;

        LogInOutLogPK(Long accountId, AccountType accountType, LogType logType) {
            this.accountId = accountId;
            this.accountType = accountType;
            this.logType = logType;
        }
    }

    private enum LogType {
        LOGIN,
        LOGOUT
    }
}
