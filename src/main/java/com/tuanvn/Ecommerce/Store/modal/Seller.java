package com.tuanvn.Ecommerce.Store.modal;

import com.tuanvn.Ecommerce.Store.domain.AccountStatus;
import com.tuanvn.Ecommerce.Store.domain.USER_ROLE;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity // danh dau la 1 bang trong batabase
@Getter // tu dong tao getter cho cac thuoc tinh
@Setter // tu dong tao setter cho cac thuoc tinh
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode // Tạo phương thức equals() và hashCode(), giúp so sánh các đối tượng Seller.
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //Thông tin cá nhân của người bán
    private String sellerName;

    private String mobile;

    @Column(unique = true, nullable = false)
    private String email;
    private String password;

    //Thông tin doanh nghiệp
    @Embedded
    private BusinessDetails businessDetails = new BusinessDetails();

    //Thông tin ngân hàng
    @Embedded
    private BankDetails bankDetails = new BankDetails();
    // Địa chỉ kho hàng
    @OneToOne(cascade = CascadeType.ALL)
    private Address pickupAddress=new Address();

    //Thông tin thuế & trạng thái tài khoản
    private String GSTIN;
    private USER_ROLE role = USER_ROLE.ROLE_SELLER;
    private boolean isEmailVerified = false;
    private AccountStatus accountStatus = AccountStatus.PENDING_VERIFICATION;

}
