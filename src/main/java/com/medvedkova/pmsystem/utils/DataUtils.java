package com.medvedkova.pmsystem.utils;

import com.medvedkova.pmsystem.exception.project.ProjectNotFoundException;

public class DataUtils {

    public static int parseInt(String id) {
        try {
            return Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new ProjectNotFoundException();
        }
    }

    public static <T> boolean isCorrectInputData(T oldData, T newData) {
        return (newData != null && !newData.equals(oldData))
                && (!(newData instanceof String) || !((String) newData).trim().isEmpty());
    }
}
