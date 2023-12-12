package com.Doggo.DoggoEx.service;

import com.Doggo.DoggoEx.dto.BoardDto;
import com.Doggo.DoggoEx.entity.Board;
import com.Doggo.DoggoEx.entity.Member;
import com.Doggo.DoggoEx.repository.BoardRepository;
import com.Doggo.DoggoEx.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    // 게시글 등록
    public boolean saveBoard(BoardDto boardDto) {
        try {
            Board board = new Board();
            Member member = memberRepository.findByMemberEmail(boardDto.getMemberEmail()).orElseThrow(
                    () -> new RuntimeException("해당 회원이 존재하지 않습니다.")
            );
            board.setBoardType(boardDto.getBoardType());
            board.setComment(boardDto.getComment());
            board.setBoardImg(boardDto.getBoardImg());
            board.setMember(member);
            boardRepository.save(board);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    // 게시글 전체 조회
    public List<BoardDto> getOneBoard() {
        List<Board> boards = boardRepository.findAll();
        List<BoardDto> boardDtos = new ArrayList<>();
        for(Board board : boards) {
            boardDtos.add(convertEntityToDto(board));
        }
        return boardDtos;}
    // 회원이 작성한 문의 조회
    public List<BoardDto> getOneBoardByMemberEmail(String memberEmail) {
        // 이 이메일(String memberEmail)을 사용해서 이메일과 일치하는 관련 정보로 반환을 하는데 BoardDto 형식 리스트로 반환
        List<Board> boards = boardRepository.findByMemberMemberEmail(memberEmail);
        //boardRepository에 선언된 함수로 디비에서 해당 이메일 기준으로 게시판 정보를 찾는 기능을 하는데 그 결과를 list<board> 형태의 변수인 'boards'에 저장
        List<BoardDto> boardDtos = new ArrayList<>();
        // 새로운 빈배열을 만드는데 for문으로 찾은 해당하는 값을 List<BoardDto>형태의 변수인 boardDtos에 넣고 리턴해서 반환한다.
        for(Board board : boards) {
            boardDtos.add(convertEntityToDto(board));
        } // boards의 리스트에 있는 각각의 보드 엔티티를 BoardDto로 변환해서 boardDtos 추가해서 반환
        return boardDtos;
    }

    // 엔티티 변환
    private BoardDto convertEntityToDto(Board board) {
        BoardDto boardDto = new BoardDto();
        boardDto.setBoardType(board.getBoardType());
        boardDto.setComment(board.getComment());
        boardDto.setBoardImg(board.getBoardImg());
        boardDto.setMemberEmail(board.getMember().getMemberEmail());
        boardDto.setRegDate(board.getRegDate());
        boardDto.setAnswer(board.getAnswer());

        return boardDto;
    }
}
