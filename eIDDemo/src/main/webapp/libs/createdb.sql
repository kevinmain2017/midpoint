

CREATE TABLE `m_user_type` (
  `oid` varchar(50) COLLATE utf8_bin NOT NULL,
  `user_oid` varchar(50) COLLATE utf8_bin NOT NULL,
  `type_code` varchar(20) COLLATE utf8_bin NOT NULL,
  `info` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
COMMIT;
ALTER TABLE `m_user_type`
  ADD PRIMARY KEY (`oid`);
COMMIT;

CREATE TABLE `m_type` (
  `oid` varchar(50) COLLATE utf8_bin NOT NULL,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  `code` varchar(20) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `m_type`
--
ALTER TABLE `m_type`
  ADD PRIMARY KEY (`oid`);
COMMIT;
