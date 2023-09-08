import java.util.Arrays;

public class PermutationCipher {
    public static void main(String[] args) {
        String message = "luan ";
        String key = "chu thanh";
        String encryptedMessage = encrypt(message,key);
        System.out.println("Encrypted message: " + encryptedMessage);
        String decryptedMessage = decrypt(encryptedMessage, key);
        System.out.println("Decrypted message: " + decryptedMessage);
    }

    public static String encrypt(String message, String key) {
        int[] permutation = getPermutation(key);
        int messageLength = message.length();
        int rows = (int) Math.ceil((double) messageLength / key.length());
        char[][] matrix = new char[rows][key.length()];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < key.length(); j++) {
                if (index < messageLength) {
                    matrix[i][j] = message.charAt(index++);
                } else {
                    matrix[i][j] = ' ';
                }
            }
        }
        char[][] encryptedMatrix = new char[rows][key.length()];
        for (int i = 0; i < key.length(); i++) {
            int column = permutation[i];
            for (int j = 0; j < rows; j++) {
                encryptedMatrix[j][i] = matrix[j][column];
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < key.length(); j++) {
                sb.append(encryptedMatrix[i][j]);
            }
        }
        return sb.toString();
    }

    public static String decrypt(String encryptedMessage, String key) {
        int[] permutation = getPermutation(key);
        int messageLength = encryptedMessage.length();
        int rows = messageLength / key.length();
        char[][] encryptedMatrix = new char[rows][key.length()];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < key.length(); j++) {
                encryptedMatrix[i][j] = encryptedMessage.charAt(index++);
            }
        }
        char[][] decryptedMatrix = new char[rows][key.length()];
        for (int i = 0; i < key.length(); i++) {
            int column = permutation[i];
            for (int j = 0; j < rows; j++) {
                decryptedMatrix[j][column] = encryptedMatrix[j][i];
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < key.length(); j++) {
                sb.append(decryptedMatrix[i][j]);
            }
        }
        return sb.toString().trim();
    }

    public static int[] getPermutation(String key) {
        int[] permutation = new int[key.length()];
        for (int i = 0; i < key.length(); i++) {
            permutation[i] = i;
        }
        char[] keyArray = key.toCharArray();
        for (int i = 0; i < key.length() - 1; i++) {
            for (int j = i + 1; j < key.length(); j++) {
                if (keyArray[i] > keyArray[j]) {
                    swap(permutation, i, j);
                }
            }
        }
        return permutation;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
