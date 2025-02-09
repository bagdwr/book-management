package kz.net.book_management.model.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoanStatusDto {
    public String loanId;
    public String memberId;
    public String bookId;
    public Instant loanReturnDate;
}
