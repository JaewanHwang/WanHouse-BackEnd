# **관통프로젝트5**

## ⭐ 1. 프로젝트 정보

### 프로젝트명: Happy Hous5 🏡

### 팀원: 김완상, 황재완

## ⭐ 2. 구현 현황

---

|난이도|기능|구현 여부|
|:----:|---|:---:|
|기본 기능|메인화면, 실거래가 검색 결과|✔|
|기본 기능|회원 관리|✔|
|기본 기능|로그인/로그아웃|✔|
|심화 기능|공지사항 관리|✔|

## ⭐ 기본 기능

---

### ❤ 메인화면, 실거래가 검색 결과

---

![메인화면, 실거래가 검색 결과](./READMEphoto/메인화면.gif)

### ❤ 회원 관리 및 로그인/로그아웃 관리

---

#### 1) 회원 가입-로그인

![회원 가입, 로그인](./READMEphoto/회원가입로그인.gif)

#### 2) 회원 정보 수정

![회원 정보 수정](./READMEphoto/회원정보수정.gif)

#### 3) 수정된 상태로 로그인/로그아웃

![수정된 상태로 로그인/로그아웃](./READMEphoto/수정로그인로그아웃.gif)

#### 4) 회원 탈퇴

![회원 탈퇴](./READMEphoto/회원탈퇴.gif)

### ❤ 아파트 실거래가 검색 결과

---

#### 1) 동별 아파트 조회

![동별아파트조회](./READMEphoto/동별아파트조회.gif)

#### 2) 아파트별 거래 내역 조회

![아파트별조회](./READMEphoto/아파트별거래내역조회.gif)

## ⭐ **심화기능**

---

### ❤ 공지사항(게시판) 관리

#### 1) 게시판 작성 및 조회

![게시판작성및조회](./READMEphoto/게시글작성및조회.gif)

#### 2) 게시글 수정

![게시글수정](./READMEphoto/게시글수정.gif)

#### 3) 게시글 삭제

![게시글삭제](./READMEphoto/게시글삭제.gif)

---

## Algorithm PJT
### 1. 제목: 알고리즘 적용 기획서 #1
### 2. 내용: 다양한 조건분기 브루트포스 알고리즘을 이용한 검색 필터링 및 정렬 서비스
### 3. 적용 알고리즘: 다양한 조건분기 브루트포스
### 4. 알고리즘 개요
다양한 조건분기 브루트포스 알고리즘은 상세 검색, 필터링, 정렬과 같은 여러 검색 조건들이
수많은 경우의 수로 조합이 될때 하나의 API를 통해 처리하는 알고리즘입니다.

검색 조건은 동코드, 정렬(아파트 이름순, 전용면적순, 거래일순, 거래금액순), 거래금액, 전용면적, 아파트 준공 연도를 이용하며
이를 처리하려면 다양한 API 개발이 필요하지만 하나의 API로 처리할 수 있습니다.

Map을 사용해 선택한 검색 조건의 이름을 key로, 그 값을 value로 저장하여 서버에서는 해당 맵의 key값의
존재유무를 통해 다양한 조건 분기 처리가 가능해집니다.

### 5. 적용 서비스: 상세 검색, 검색 결과 정렬, 필터링 검색
### 6. 적용 서비스 개발 개요
FrontEnd 코드인 아파트 매매 검색 결과를 나타내는 JSP 파일에서 ajax를 이용하여 선택한 검색 조건들에 대해 
queryString을 동적으로 생성하여 서버에 GET 메서드로 전달하였습니다.
서버에서는 `/happyhouse/apt?검색조건1=value1&검색조건2=value2...`와 같은 url mapping을 다음과 같이 수행했습니다.
```java
@GetMapping("/apt")
public @ResponseBody ResponseEntity<List<HouseInfoDto>> apt(@RequestParam Map<String, Integer> filters)
    ...
}
```
이렇게 매핑하면 filters라는 map을 통해 동적으로 생성된 queryString이 key value 쌍으로 들어가게 됩니다.
filters는 그대로 MyBatis mapper 코드에서 다음과 같이 동적 query를 생성하는데 사용됩니다.
```xml
<select id="getAptInDong" parameterType="map" resultType="houseInfoDto">
        select distinct deals.aptCode, hd.area, aptName, buildYear, dongCode, dongName, jibun, lat, lng, sidoname, gugunname, recentPrice
        from (
        select h.aptCode, h.aptName, h.buildyear, h.dongCode, h.dongName, h.jibun, h.lat, h.lng, si.sidoname, gu.gugunname,
        (select dealAmount from housedeal where aptCode = h.aptCode and no = (select max(no) from housedeal where
        aptCode = h.aptCode)) recentPrice
        from houseinfo h
        left join sidocode si
        on left(h.dongcode,2) = left(si.sidocode,2)
        left join guguncode gu
        on left(h.dongcode,5) = left(gu.guguncode,5)
        where dongCode = #{dong}
        ) deals
        inner join housedeal hd
        on deals.aptCode = hd.aptCode and deals.recentPrice = hd.dealAmount
        <where>
            <if test="area != null">
                and hd.area + 0.0 >= #{area}
            </if>
            <if test="dealAmount != null">
                and (replace(recentPrice, ",", "") + 0) >= (#{dealAmount} * 10000)
            </if>
            <if test="buildYear != null">
                and buildYear >= #{buildYear}
            </if>
        </where>
        <if test="order != null">
            order by ${order}  <if test='!order.equals("aptName")'> desc</if>
        </if>
    </select>
```
이 query를 통해서 사용자가 검색한 동에 해당하는 모든 아파트 매매 내역에서 가장 최근에 거래된
매매가와 아파트 정보들을 nested subquery로 가져와 아파트 매매 테이블과 inner join을 통해
전용면적을 같이 가져오고 사용자가 입력한 검색조건에 맞춰 다양한 조건 분기로 동적 where 쿼리를
완성합니다. 마지막으로 사용자가 입력한 정렬조건에 따라 정렬을 수행해 query 결과를 돌려줍니다.
이를 통해 저희 팀은 다양한 검색 조건에 대한 상세 검색 및 필터링, 정렬을 수행할 수 있습니다.