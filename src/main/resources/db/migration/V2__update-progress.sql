ALTER TABLE progress DROP CONSTRAINT IF EXISTS chk_progress_status;
ALTER TABLE progress ADD CONSTRAINT chk_progress_status 
    CHECK (status IN ('NOT_STARTED', 'STARTED', 'COMPLETED'));