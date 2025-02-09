package kz.net.book_management.model.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoanDto {
    public String id;
    public String bookId;
    public String bookTitle;
    public String memberId;
    public String memberFullName;
    public Instant loanDate;
    public Instant loanReturnDate;
}
