package kr.or.ddit.vo;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.apache.commons.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.administration.vo.RegisterVO;
import kr.or.ddit.administration.vo.TuitionVO;
import kr.or.ddit.validate.constraints.FileMimeChecker;
import kr.or.ddit.enumpkg.UserCode;
import kr.or.ddit.validate.constraints.TelNumber;
import kr.or.ddit.validate.groups.FindIdGroup;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.groups.ResetPasswordAndUnlockAccountGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(exclude= {"memReg2", "memPass", "memImg"})
@EqualsAndHashCode(of="memId")
public class MemberVO implements Serializable{

	@NotBlank(message="아이디를 입력해주세요.",groups= {ResetPasswordAndUnlockAccountGroup.class})
	private String memId;		// 학번/사번
	
	private String userCode;
	@NotBlank(message="이름을 입력해주세요.", groups= {FindIdGroup.class, ResetPasswordAndUnlockAccountGroup.class, UpdateGroup.class, InsertGroup.class})
	private String memName;		// 이름
	private String memEname;
	private Integer memAge;
	private String memBirth;
	
	@NotBlank(message="주민등록번호 앞자리를 입력해주세요.", groups= {FindIdGroup.class, InsertGroup.class} )
	@Size(message="주민등록번호 앞자리는 6자리여야 합니다.", min=6, max=6, groups= {FindIdGroup.class, InsertGroup.class})
	private String memReg1;		// 주민번호 앞자리
	
	@NotBlank(message="주민등록번호 뒷자리를 입력해주세요.", groups= {FindIdGroup.class, InsertGroup.class})
	@Size(min=7, max=7, groups= {InsertGroup.class})
	@Size(message="주민등록번호 뒷자리는 1자리여야 합니다.", min=1, max=1, groups= {FindIdGroup.class})
	@Size(message="주민등록번호 뒷자리는 7자리여야 합니다.", min=7, max=7, groups= {InsertGroup.class})
	private String memReg2;		// 주민번호 뒷자리
	
	private String memGender;
	
	@NotBlank(message="전화번호를 입력해주세요.", groups= {FindIdGroup.class, ResetPasswordAndUnlockAccountGroup.class, UpdateGroup.class, InsertGroup.class}) 
	@TelNumber(message="전화번호를 형식에 맞게 입력해주세요.(ex 010-1234-5678)", groups= {FindIdGroup.class, ResetPasswordAndUnlockAccountGroup.class, UpdateGroup.class, InsertGroup.class})
	private String memTel;		// 휴대전화번호
	
	@NotBlank(message="주소를 입력해주세요.", groups= {InsertGroup.class, UpdateGroup.class})
	private String memAdd1;
	
	@NotBlank(message="주소를 입력해주세요.", groups= {InsertGroup.class, UpdateGroup.class})
	private String memAdd2;
	@NotEmpty(message="이메일을 입력해주세요", groups= {UpdateGroup.class})
	private String memMail;
	private String memEmpno;
	
	@NotBlank(message="비밀번호를 입력해주세요.", groups= {InsertGroup.class} )
	@Size(message="비밀번호는 4-15 자리여야 합니다." ,min=4, max=15, groups= {InsertGroup.class})
	private String memPass;
	private String rankCode;
	private String deptCode;
	

	private String memJoindate;
	private String memResdate;
	private String memPeriod;
	
	private String memNation;
	private String memInsurance;
	private String memDelete;
	private String memStuno;
	

	private String memEntdate;
	private String memGradate;
	private String memSubtel;
	private String memMajor;
	private String memTrack;
	private String trackName;
	private String majorDouble;
	private String memTrack2;
	private String majorMinor;
	private String memTrack3;
	private String memGraduate;
	

	private String admissionCode;
	private String memAdviser;
	private String memAdviser2;
	private String memSemester;
	private String memCommute;
	
	private Integer memLoginfailcnt;	// 로그인 실패 횟수
	private String userCate;
	
	private List<String> memRoles;
	
	// 해당 회원(학생)의 학적 정보
	private List<RegisterVO> register;
	
	// 해당 회원(직원)의 임금테이블 정보
	private Set<PayTableVO> payTableList;
	
	// 해당 회원(직원)의 임금내역 정보
	private Set<PayVO> payList;
	
	private MajorVO major1;				// 주전공 정보
	private MemberVO adviser1; 			// 주전공 지도교수 정보
	private MemberVO assistant1;		// 조교 정보
	
	private String memCollege;			// 소속 단과대학
	private Integer memRegisterlimit;	// 수강제한학점
	
	private List<ClassListVO> semesterClassList; // 현재학기에 수강중인 강의 리스트
	
	private Integer memAbsLimit; // 휴학가능 학기
	private Integer memMiLimit; // 군휴학가능여부

	private SearchVO search1;
	private TuitionVO tuition1;
	
	private byte[] memImg;
	
	@FileMimeChecker(mime="image")
	private MultipartFile memImage;
	public void setMemImage(MultipartFile memImage) throws IOException {
		if(memImage==null || memImage.isEmpty()) return;
		this.memImage = memImage;
		memImg = memImage.getBytes();
	}
	
	public String getBase64Image() {
		if(memImg==null) return null;
		else
			return Base64.encodeBase64String(memImg);
	}
	
	public String getUserCodeName() {
		UserCode userCodeEnum = UserCode.valueOf(this.userCode);
		
		return userCodeEnum.getUserCodeName();
	}
}
