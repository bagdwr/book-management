package kz.net.book_management.model.enums;

public enum GenreEnum {
    FANTASY("Фэнтэзи"),
    BIOGRAPHY("Биография"),
    HISTORY("История"),
    HORROR("Хоррор"),
    THRILLER("Триллер"),
    PHILOSOPHY("Философия"),
    RELIGION("Религия"),
    SCIENCE("Наука"),
    BUSINESS("Бизнес"),
    TECHNOLOGY("Технологии");

    GenreEnum(String value) {
        this.value = value;
    }

    private final String value;

    public String get() {
        return this.value;
    }

}
