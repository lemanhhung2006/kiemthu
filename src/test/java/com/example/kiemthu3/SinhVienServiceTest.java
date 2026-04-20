package com.example.kiemthu3;

import com.example.kiemthu3.entity.SinhVien;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("SinhVienService Tests")
public class SinhVienServiceTest {

    private SinhVienService sinhVienService;

    @BeforeEach
    void setUp() {
        sinhVienService = new SinhVienService();
    }

    // ==================== TEST THÊM SINH VIÊN ====================

    @Test
    @DisplayName("Thêm sinh viên hợp lệ - thành công")
    void testThemSinhVien_HopLe() {
        SinhVien sv = new SinhVien("SV001", "Nguyen Van A", 20, 8.5f, 1, "CNTT");
        sinhVienService.themSinhVien(sv);

        assertEquals(1, sinhVienService.getDanhSachSinhVien().size());
        assertEquals("SV001", sinhVienService.getDanhSachSinhVien().get(0).getMaSV());
    }

    @Test
    @DisplayName("Thêm nhiều sinh viên hợp lệ - thành công")
    void testThemNhieuSinhVien_HopLe() {
        SinhVien sv1 = new SinhVien("SV001", "Nguyen Van A", 20, 8.5f, 1, "CNTT");
        SinhVien sv2 = new SinhVien("SV002", "Tran Thi B", 21, 7.0f, 2, "Kinh Te");

        sinhVienService.themSinhVien(sv1);
        sinhVienService.themSinhVien(sv2);

        assertEquals(2, sinhVienService.getDanhSachSinhVien().size());
    }

    @Test
    @DisplayName("Thêm sinh viên trùng mã - ném exception")
    void testThemSinhVien_TrungMa() {
        SinhVien sv1 = new SinhVien("SV001", "Nguyen Van A", 20, 8.5f, 1, "CNTT");
        SinhVien sv2 = new SinhVien("SV001", "Tran Thi B", 21, 7.0f, 2, "Kinh Te");

        sinhVienService.themSinhVien(sv1);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            sinhVienService.themSinhVien(sv2);
        });
        assertTrue(exception.getMessage().contains("Mã sinh viên đã tồn tại"));
    }

    @Test
    @DisplayName("Thêm sinh viên null - ném exception")
    void testThemSinhVien_Null() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            sinhVienService.themSinhVien(null);
        });
        assertEquals("Sinh viên không được null", exception.getMessage());
    }

    // ==================== TEST SỬA SINH VIÊN ====================

    @Test
    @DisplayName("Sửa sinh viên hợp lệ - thành công")
    void testSuaSinhVien_HopLe() {
        SinhVien sv = new SinhVien("SV001", "Nguyen Van A", 20, 8.5f, 1, "CNTT");
        sinhVienService.themSinhVien(sv);

        SinhVien svMoi = new SinhVien("SV001", "Nguyen Van A Updated", 21, 9.0f, 2, "CNPM");
        sinhVienService.suaSinhVien(svMoi);

        SinhVien result = sinhVienService.timSinhVienTheoMa("SV001");
        assertNotNull(result);
        assertEquals("Nguyen Van A Updated", result.getTen());
        assertEquals(21, result.getTuoi());
        assertEquals(9.0f, result.getDiemTrungBinh(), 0.01);
        assertEquals(2, result.getKyHoc());
        assertEquals("CNPM", result.getChuyenNganh());
    }

    @Test
    @DisplayName("Sửa sinh viên không tồn tại - ném exception")
    void testSuaSinhVien_KhongTonTai() {
        SinhVien sv = new SinhVien("SV999", "Nguyen Van X", 20, 8.5f, 1, "CNTT");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            sinhVienService.suaSinhVien(sv);
        });
        assertTrue(exception.getMessage().contains("Không tìm thấy sinh viên"));
    }

    @Test
    @DisplayName("Sửa sinh viên - chỉ thay đổi sinh viên đúng mã")
    void testSuaSinhVien_ChiThayDoiDungMa() {
        SinhVien sv1 = new SinhVien("SV001", "Nguyen Van A", 20, 8.5f, 1, "CNTT");
        SinhVien sv2 = new SinhVien("SV002", "Tran Thi B", 21, 7.0f, 2, "Kinh Te");
        sinhVienService.themSinhVien(sv1);
        sinhVienService.themSinhVien(sv2);

        SinhVien svMoi = new SinhVien("SV001", "Nguyen Van A Updated", 22, 9.5f, 3, "CNPM");
        sinhVienService.suaSinhVien(svMoi);

        // SV001 đã được cập nhật
        SinhVien result1 = sinhVienService.timSinhVienTheoMa("SV001");
        assertEquals("Nguyen Van A Updated", result1.getTen());

        // SV002 không bị ảnh hưởng
        SinhVien result2 = sinhVienService.timSinhVienTheoMa("SV002");
        assertEquals("Tran Thi B", result2.getTen());
    }

    // ==================== TEST VALIDATE - CÁC TRƯỜNG KHÔNG ĐƯỢC ĐỂ TRỐNG ====================

    @Test
    @DisplayName("Validate: maSV null - ném exception")
    void testValidate_MaSV_Null() {
        SinhVien sv = new SinhVien(null, "Nguyen Van A", 20, 8.5f, 1, "CNTT");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            sinhVienService.themSinhVien(sv);
        });
        assertEquals("Mã sinh viên không được để trống", exception.getMessage());
    }

    @Test
    @DisplayName("Validate: maSV rỗng - ném exception")
    void testValidate_MaSV_Rong() {
        SinhVien sv = new SinhVien("", "Nguyen Van A", 20, 8.5f, 1, "CNTT");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            sinhVienService.themSinhVien(sv);
        });
        assertEquals("Mã sinh viên không được để trống", exception.getMessage());
    }

    @Test
    @DisplayName("Validate: maSV chỉ chứa khoảng trắng - ném exception")
    void testValidate_MaSV_KhoangTrang() {
        SinhVien sv = new SinhVien("   ", "Nguyen Van A", 20, 8.5f, 1, "CNTT");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            sinhVienService.themSinhVien(sv);
        });
        assertEquals("Mã sinh viên không được để trống", exception.getMessage());
    }

    @Test
    @DisplayName("Validate: tên null - ném exception")
    void testValidate_Ten_Null() {
        SinhVien sv = new SinhVien("SV001", null, 20, 8.5f, 1, "CNTT");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            sinhVienService.themSinhVien(sv);
        });
        assertEquals("Tên sinh viên không được để trống", exception.getMessage());
    }

    @Test
    @DisplayName("Validate: tên rỗng - ném exception")
    void testValidate_Ten_Rong() {
        SinhVien sv = new SinhVien("SV001", "", 20, 8.5f, 1, "CNTT");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            sinhVienService.themSinhVien(sv);
        });
        assertEquals("Tên sinh viên không được để trống", exception.getMessage());
    }

    @Test
    @DisplayName("Validate: tuổi = 0 - ném exception")
    void testValidate_Tuoi_BangKhong() {
        SinhVien sv = new SinhVien("SV001", "Nguyen Van A", 0, 8.5f, 1, "CNTT");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            sinhVienService.themSinhVien(sv);
        });
        assertEquals("Tuổi sinh viên phải lớn hơn 0", exception.getMessage());
    }

    @Test
    @DisplayName("Validate: tuổi âm - ném exception")
    void testValidate_Tuoi_Am() {
        SinhVien sv = new SinhVien("SV001", "Nguyen Van A", -1, 8.5f, 1, "CNTT");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            sinhVienService.themSinhVien(sv);
        });
        assertEquals("Tuổi sinh viên phải lớn hơn 0", exception.getMessage());
    }

    @Test
    @DisplayName("Validate: điểm trung bình < 0 - ném exception")
    void testValidate_DiemTrungBinh_Am() {
        SinhVien sv = new SinhVien("SV001", "Nguyen Van A", 20, -1.0f, 1, "CNTT");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            sinhVienService.themSinhVien(sv);
        });
        assertEquals("Điểm trung bình phải từ 0 đến 10", exception.getMessage());
    }

    @Test
    @DisplayName("Validate: điểm trung bình > 10 - ném exception")
    void testValidate_DiemTrungBinh_LonHon10() {
        SinhVien sv = new SinhVien("SV001", "Nguyen Van A", 20, 11.0f, 1, "CNTT");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            sinhVienService.themSinhVien(sv);
        });
        assertEquals("Điểm trung bình phải từ 0 đến 10", exception.getMessage());
    }

    @Test
    @DisplayName("Validate: kỳ học = 0 - ném exception")
    void testValidate_KyHoc_BangKhong() {
        SinhVien sv = new SinhVien("SV001", "Nguyen Van A", 20, 8.5f, 0, "CNTT");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            sinhVienService.themSinhVien(sv);
        });
        assertEquals("Kỳ học phải lớn hơn 0", exception.getMessage());
    }

    @Test
    @DisplayName("Validate: chuyên ngành null - ném exception")
    void testValidate_ChuyenNganh_Null() {
        SinhVien sv = new SinhVien("SV001", "Nguyen Van A", 20, 8.5f, 1, null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            sinhVienService.themSinhVien(sv);
        });
        assertEquals("Chuyên ngành không được để trống", exception.getMessage());
    }

    @Test
    @DisplayName("Validate: chuyên ngành rỗng - ném exception")
    void testValidate_ChuyenNganh_Rong() {
        SinhVien sv = new SinhVien("SV001", "Nguyen Van A", 20, 8.5f, 1, "");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            sinhVienService.themSinhVien(sv);
        });
        assertEquals("Chuyên ngành không được để trống", exception.getMessage());
    }

    // ==================== TEST TÌM SINH VIÊN ====================

    @Test
    @DisplayName("Tìm sinh viên theo mã - tìm thấy")
    void testTimSinhVien_TimThay() {
        SinhVien sv = new SinhVien("SV001", "Nguyen Van A", 20, 8.5f, 1, "CNTT");
        sinhVienService.themSinhVien(sv);

        SinhVien result = sinhVienService.timSinhVienTheoMa("SV001");
        assertNotNull(result);
        assertEquals("Nguyen Van A", result.getTen());
    }

    @Test
    @DisplayName("Tìm sinh viên theo mã - không tìm thấy")
    void testTimSinhVien_KhongTimThay() {
        SinhVien result = sinhVienService.timSinhVienTheoMa("SV999");
        assertNull(result);
    }

    // ==================== TEST VALIDATE KHI SỬA ====================

    @Test
    @DisplayName("Sửa sinh viên với tên rỗng - ném exception")
    void testSuaSinhVien_Validate_TenRong() {
        SinhVien sv = new SinhVien("SV001", "Nguyen Van A", 20, 8.5f, 1, "CNTT");
        sinhVienService.themSinhVien(sv);

        SinhVien svMoi = new SinhVien("SV001", "", 20, 8.5f, 1, "CNTT");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            sinhVienService.suaSinhVien(svMoi);
        });
        assertEquals("Tên sinh viên không được để trống", exception.getMessage());
    }

    @Test
    @DisplayName("Sửa sinh viên với maSV null - ném exception")
    void testSuaSinhVien_Validate_MaSVNull() {
        SinhVien svMoi = new SinhVien(null, "Nguyen Van A", 20, 8.5f, 1, "CNTT");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            sinhVienService.suaSinhVien(svMoi);
        });
        assertEquals("Mã sinh viên không được để trống", exception.getMessage());
    }
}
