package com.be24.api.abc.repository;


// ISP 적용 : 각각 나눠진 인터페이스를 상속, 너무 오바하면 이렇게 기능별로 쪼개게 됨
//          실제로는 큰 기능 or 클라이언트 별로
//          ex) DB 기능들만 모아둔 인터페이스, 파일 업로드 처리만하는 인터페이스
public interface AbcRepository
        extends
        AbcCreateRepository,
        AbcReadRepository,
        AbcUpdateRepository,
        AbcDeleteRepository
{

}
