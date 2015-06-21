/**
 * 404 Studio
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.prj.common.util;

/**
 * 
 * @author yiliang.gyl
 * @version $Id: PathEnum.java, v 0.1 Jun 21, 2015 10:59:35 AM yiliang.gyl Exp $
 */
public class PathEnum {
    enum AccountRole {
        ANY, ADMINISTRATOR, ALL_TEACHER, NOR_TEACHER, LAB_TEACHER, STUDENT
    }

    public enum ReservationType {
        classReservation, studentReservation
    }

    public enum ReservationStatus {
        ALL, PENDING, APPROVED, REJECTED
    }
}
