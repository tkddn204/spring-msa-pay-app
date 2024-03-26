package net.rightpair.exception;

public class MembershipInvalidException extends RuntimeException {
    public MembershipInvalidException() {
        super("해당 멤버쉽은 유효하지 않습니다.");
    }
}
