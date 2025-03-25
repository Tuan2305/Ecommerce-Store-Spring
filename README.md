13/3: update file SellerController, VerificationCodeRepository

SellerController: dòng 54 trở đi: gửi mã OTP (One-Time Password) để xác thực tài khoản email của người bán (Seller) khi họ tạo tài khoản.

Endpoint    :Chức năng chính	:Phương thức HTTP
/login	:Đăng nhập bằng OTP	:POST
/verify/{otp}	:Xác thực email qua mã OTP	:PATCH
/	Tạo tài khoản người bán và gửi OTP	:POST
/profile	Lấy thông tin tài khoản từ JWT	:GET
/report (comment)	Lấy báo cáo doanh thu	:GET
/ (get all)	Lấy danh sách người bán	:GET
/ (update)	Cập nhật thông tin người bán	:PATCH
/{id} (delete)	Xóa tài khoản người bán	:DELETE

14/3 : tạo mới file SellerException

9:41:15