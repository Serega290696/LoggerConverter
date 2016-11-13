package org.beltser;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.apache.commons.codec.binary.Hex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomConverter extends ClassicConverter {
    static int counter = 0;

    @Override
    public String convert(ILoggingEvent event) {
        String message = event.getMessage();

        message = message
                .replaceAll("\n", "")
                .replaceAll("\\\\", "\\\\\\\\")
                .replaceAll("\\{", "\\\\{")
                .replaceAll("\\)", "\\\\)")
                .replaceAll("\\(", "\\\\(")
                .replaceAll("\\^", "\\\\^")
                .replaceAll("\\+", "\\\\+")
                .replaceAll("\\*", "\\\\*")
        ;
//        System.out.println("message = " + message);

        Pattern pattern = Pattern.compile("(\"bytes\": \"(.*?)\"})");
        Matcher matcher = pattern.matcher(message);
        String newMessage = message;
//        System.out.println("Count: " + matcher.groupCount());
        for (int i = 2; 2 <= matcher.groupCount() && matcher.find(); ) {
            String groupa = matcher.group(i);
//            System.out.println(i + ". " + "groupa = " + groupa);
//                System.out.println(i + ". ");

            String group = matcher.group(i);
//            System.out.println("group = " + group);

            String hex = Hex.encodeHexString(group.getBytes());
//            System.out.println("hex = " + hex);

            boolean matches = newMessage.matches(group);
//            System.out.println("matches = " + matches);
            newMessage = Main.replaceAllSubstrings(newMessage, group, hex, true);
//            System.out.println("newMessage = " + newMessage);
//            System.out.println();
//            System.out.println();
//            }
        }

        newMessage = newMessage
                .replaceAll("\\\\\\\\", "\\\\")
                .replaceAll("\\\\\\{", "{")
                .replaceAll("\\\\\\(", "(")
                .replaceAll("\\\\\\)", ")")
        ;
//        System.out.println("newMessage = " + newMessage);

        return newMessage;
//        String message = event.getMessage();
//        StringBuilder builder = new StringBuilder();
//        for (int i = 0; i < message.length(); i++) {
//            char c = message.charAt(i);
//            if (!Main.isByte(c)) {
//                builder.append(c);
//            } else {
//                String newChar = Hex.encodeHexString(new byte[]{(byte) c});
//                builder.append(newChar);
//            }
//        }
//        return builder.toString();
    }

    private boolean isSymbol(char c) {
        return false;
    }
}
