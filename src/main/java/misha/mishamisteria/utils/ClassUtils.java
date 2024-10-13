package misha.mishamisteria.utils;

public class ClassUtils {

    /**
     * Generate simple class name for item/block registering
     * Eg: BlockWitchHat -> witch_hat
     */
    public static String getSimpleClassName(Class<?> clazz) {
        return clazz.getSimpleName()
                .replaceAll("Block", "")
                .replaceAll("Item", "")
                .replaceAll("\\d+", "")
                .replaceAll("(.)([A-Z])", "$1_$2")
                .toLowerCase();
    }

}
