package io.deeplay.lab.db;

import io.deeplay.lab.data.Round;

import java.io.Closeable;
import java.util.List;
import java.util.Optional;

public interface Database extends Closeable {
  int CHUNK_SIZE = 500;

  Optional<Long> getOrInsertLocationAsId(final String location);

  Optional<Long> insertRoundAsId(final Round round);
  
  void insertUnitsIfNotExist(final Round round);

  void insertHistoricalIterations(final long round_id, final Round round);

  void insertRound(final Round round);

  void insertUnitsFromRounds(List<Round> rounds);

  void insertLocationsFromRounds(List<Round> rounds);

  void insertRoundsFromRounds(List<Round> rounds);

  void insertHistoricalIterationsFromRounds(List<Round> rounds);

  void insertRounds(List<Round> rounds);
}
