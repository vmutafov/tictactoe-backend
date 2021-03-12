package io.vm.tictactoe.db;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GameBoardRepository extends JpaRepository<GameBoardEntity, Long> {
}
